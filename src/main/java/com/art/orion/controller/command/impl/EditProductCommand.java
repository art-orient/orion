package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
import com.art.orion.controller.command.util.TextHandler;
import com.art.orion.controller.command.util.XssProtection;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.art.orion.controller.command.PagePath.EDIT_PRODUCT_PAGE;
import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.CLOTHING;
import static com.art.orion.util.Constant.DESCRIPTION_EN;
import static com.art.orion.util.Constant.DESCRIPTION_RU;
import static com.art.orion.util.Constant.PRODUCT;
import static com.art.orion.util.Constant.PRODUCT_ID;
import static com.art.orion.util.Constant.SHOES;

/**
 * The command is responsible for editing product information
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class EditProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductService productService;

    public EditProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String category = XssProtection.replaceBrackets(req.getParameter(CATEGORY));
        req.setAttribute(CATEGORY, category);
        int productId = RequestParseNumberHelper.getInt(req, PRODUCT_ID);
        req.setAttribute(PRODUCT_ID, productId);
        try {
            switch (category) {
                case SHOES -> {
                    Optional<Shoes> optionalShoes = productService.findShoesById(productId);
                    if (optionalShoes.isPresent()) {
                        Shoes shoes = optionalShoes.get();
                        buildDescriptions(req, shoes.getProductDetails());
                        req.setAttribute(PRODUCT, shoes);
                    }
                }
                case CLOTHING -> {
                    Optional<Clothing> optionalClothing = productService.findClothingById(productId);
                    if (optionalClothing.isPresent()) {
                        Clothing clothing = optionalClothing.get();
                        buildDescriptions(req, clothing.getProductDetails());
                        req.setAttribute(PRODUCT, clothing);
                    }
                }
                case ACCESSORIES -> {
                    Optional<Accessory> optionalAccessory = productService.findAccessoryById(productId);
                    if (optionalAccessory.isPresent()) {
                        Accessory accessory = optionalAccessory.get();
                        buildDescriptions(req, accessory.getProductDetails());
                        req.setAttribute(PRODUCT, accessory);
                    }
                }
                default -> logger.log(Level.ERROR, () -> "Invalid category while editing of product");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        logger.log(Level.DEBUG, "Go to edit product page");
        return EDIT_PRODUCT_PAGE;
    }

    private void buildDescriptions(HttpServletRequest request, ProductDetails productDetails) {
        String descriptionRu = TextHandler.createTextFromList(productDetails.getDescriptionRu());
        String descriptionEn = TextHandler.createTextFromList(productDetails.getDescriptionEn());
        request.setAttribute(DESCRIPTION_RU, descriptionRu);
        request.setAttribute(DESCRIPTION_EN, descriptionEn);
    }
}
