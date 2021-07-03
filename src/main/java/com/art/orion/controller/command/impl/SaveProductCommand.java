package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.ImageProcessor;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.service.ProductService;
import com.art.orion.model.validator.ProductValidator;
import com.art.orion.util.ConfigManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static com.art.orion.util.Constant.BRAND;
import static com.art.orion.util.Constant.MODEL_NAME;
import static com.art.orion.util.Constant.DESCRIPTION_RU;
import static com.art.orion.util.Constant.DESCRIPTION_EN;
import static com.art.orion.util.Constant.COST;
import static com.art.orion.util.Constant.AVAILABILITY;
import static com.art.orion.util.Constant.ACTIVE;
import static com.art.orion.util.Constant.TYPE_RU;
import static com.art.orion.util.Constant.TYPE_EN;

public class SaveProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String typeRu = req.getParameter(TYPE_RU);
        String typeEn = req.getParameter(TYPE_EN);
        String brand = req.getParameter(BRAND);
        String modelName = req.getParameter(MODEL_NAME);
        String descriptionRu = req.getParameter(DESCRIPTION_RU);
        String descriptionEn = req.getParameter(DESCRIPTION_EN);
        String category = (String) req.getSession().getAttribute("category");
        String filename = ImageProcessor.uploadImage(req, brand, modelName);
        BigDecimal cost = BigDecimal.ZERO;
        try {
            cost = BigDecimal.valueOf(Double.parseDouble(req.getParameter(COST)));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, () -> "This price is not BigDecimal - " + req.getParameter(COST));
        }
        int availability = Integer.parseInt(req.getParameter(AVAILABILITY));
        boolean active = Boolean.parseBoolean(req.getParameter(ACTIVE));
        ProductDetails productDetails = new ProductDetails();
        if (ProductValidator.isProductValid(brand, modelName, cost)) {
            productDetails.setBrand(brand);
            productDetails.setModelName(modelName);
            productDetails.setDescriptionRu(descriptionRu);
            productDetails.setDescriptionEn(descriptionEn);
            productDetails.setImgPath(filename);
            productDetails.setCost(cost);
            productDetails.setActive(active);
        }
        switch (category) {
            case "accessories" -> {
                Accessory accessory = new Accessory(productDetails, typeRu, typeEn, availability);
                logger.log(Level.DEBUG, () -> "Created an accessory - " + accessory);
                ProductService.createProduct(accessory);
            }
            case "clothing" -> logger.log(Level.DEBUG, () -> "Created clothing - ");
            case "shoes" -> logger.log(Level.DEBUG, () -> "Created shoes - ");
            default -> logger.log(Level.ERROR, "No category of product");
        }
        return ConfigManager.getProperty("page.productManagement");
    }
}
