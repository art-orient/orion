package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.ImageProcessor;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
import com.art.orion.controller.command.util.TextHandler;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import com.art.orion.model.validator.ProductValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.art.orion.controller.command.PagePath.ACCESSORIES_REDIRECT_PAGE;
import static com.art.orion.controller.command.PagePath.CLOTHING_REDIRECT_PAGE;
import static com.art.orion.controller.command.PagePath.INDEX_PAGE;
import static com.art.orion.controller.command.PagePath.SHOES_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.ACTIVE;
import static com.art.orion.util.Constant.AVAILABILITY;
import static com.art.orion.util.Constant.BRAND;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.CHANGE_IMAGE;
import static com.art.orion.util.Constant.CLOTHING;
import static com.art.orion.util.Constant.COLOR;
import static com.art.orion.util.Constant.COST;
import static com.art.orion.util.Constant.DESCRIPTION_EN;
import static com.art.orion.util.Constant.DESCRIPTION_RU;
import static com.art.orion.util.Constant.MODEL_NAME;
import static com.art.orion.util.Constant.PRODUCT_ID;
import static com.art.orion.util.Constant.SHOES;
import static com.art.orion.util.Constant.TYPE_EN;
import static com.art.orion.util.Constant.TYPE_RU;

public class UpdateProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductService productService;

    public UpdateProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String category = req.getParameter(CATEGORY);
        req.getSession().setAttribute(CATEGORY, category);
        int productId = RequestParseNumberHelper.getInt(req, PRODUCT_ID);
        String page = INDEX_PAGE;
        try {
            switch (category) {
                case ACCESSORIES -> {
                    Accessory accessory = createAccessory(req, productId);
                    productService.updateProduct(accessory);
                    logger.log(Level.DEBUG, () -> "Updated an accessory with id = " + productId);
                    page = ACCESSORIES_REDIRECT_PAGE;
                }
                case CLOTHING -> {
                    Clothing clothing = createClothing(req, productId);
                    productService.updateProduct(clothing);
                    logger.log(Level.DEBUG, () -> "Updated clothing with id = " + productId);
                    page = CLOTHING_REDIRECT_PAGE;
                }
                case SHOES -> {
                    Shoes shoes = createShoes(req, productId);
                    productService.updateProduct(shoes);
                    logger.log(Level.DEBUG, () -> "Updated shoes with id = " + productId);
                    page = SHOES_REDIRECT_PAGE;
                }
                default -> logger.log(Level.ERROR, "No category of product");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    private Accessory createAccessory(HttpServletRequest req, int productId) throws ServiceException {
        String typeRu = req.getParameter(TYPE_RU);
        String typeEn = req.getParameter(TYPE_EN);
        int availability = RequestParseNumberHelper.getInt(req, AVAILABILITY);
        Optional<Accessory> optionalAccessory = productService.findAccessoryById(productId);
        if (optionalAccessory.isPresent()) {
            Accessory accessory = optionalAccessory.get();
            accessory.setTypeRu(typeRu);
            accessory.setTypeEn(typeEn);
            accessory.setAvailability(availability);
            createProductDetails(req, accessory.getProductDetails());
            return accessory;
        } else {
            throw new ServiceException("No an accessory found with id = " + productId);
        }
    }

    private Clothing createClothing(HttpServletRequest req, int productId) throws ServiceException {
        String typeRu = req.getParameter(TYPE_RU);
        String typeEn = req.getParameter(TYPE_EN);
        String color = req.getParameter(COLOR);
        Optional<Clothing> optionalClothing = productService.findClothingById(productId);
        if (optionalClothing.isPresent()) {
            Clothing clothing = optionalClothing.get();
            clothing.setTypeRu(typeRu);
            clothing.setTypeEn(typeEn);
            clothing.setColor(color);
            createProductDetails(req, clothing.getProductDetails());
            return clothing;
        } else {
            throw new ServiceException("No clothing found with id = " + productId + " is not exist");
        }
    }

    private Shoes createShoes(HttpServletRequest req, int productId) throws ServiceException {
        String typeRu = req.getParameter(TYPE_RU);
        String typeEn = req.getParameter(TYPE_EN);
        String color = req.getParameter(COLOR);
        Optional<Shoes> optionalShoes = productService.findShoesById(productId);
        if (optionalShoes.isPresent()) {
            Shoes shoes = optionalShoes.get();
            shoes.setTypeRu(typeRu);
            shoes.setTypeEn(typeEn);
            shoes.setColor(color);
            createProductDetails(req, shoes.getProductDetails());
            return shoes;
        } else {
            throw new ServiceException("No shoes found with id = " + productId + " is not exist");
        }
    }

    private void createProductDetails(HttpServletRequest req, ProductDetails productDetails) {
        String brand = req.getParameter(BRAND);
        String modelName = req.getParameter(MODEL_NAME);
        List<String> descriptionRu = TextHandler.createListFromText(req.getParameter(DESCRIPTION_RU));
        List<String> descriptionEn = TextHandler.createListFromText(req.getParameter(DESCRIPTION_EN));
        BigDecimal cost = RequestParseNumberHelper.getBigDecimal(req, COST);
        boolean active = Boolean.parseBoolean(req.getParameter(ACTIVE));
        if (ProductValidator.isProductValid(brand, modelName, cost)) {
            productDetails.setBrand(brand);
            productDetails.setModelName(modelName);
            productDetails.setDescriptionRu(descriptionRu);
            productDetails.setDescriptionEn(descriptionEn);
            if (req.getParameter(CHANGE_IMAGE) != null) {
                String imagePath = ImageProcessor.uploadImage(req, brand, modelName);
                productDetails.setImgPath(imagePath);
            }
            productDetails.setCost(cost);
            productDetails.setActive(active);
        }
    }
}
