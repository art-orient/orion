package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.ImageProcessor;
import com.art.orion.util.ConfigManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static com.art.orion.util.Constant.*;

public class SaveProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String brand = req.getParameter(BRAND);
        String modelName = req.getParameter(MODEL_NAME);
        String descriptionRu = req.getParameter(DESCRIPTION_RU);
        String descriptionEn = req.getParameter(DESCRIPTION_EN);
//        String filename = ImageProcessor.uploadImage(req, brand, modelName);
//        String imagePath = req.getParameter(IMAGE);
        try {
            BigDecimal cost = BigDecimal.valueOf(Double.parseDouble(req.getParameter(COST)));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "This price is not BigDecimal - " + req.getParameter(COST));
        }
        boolean active = Boolean.valueOf(req.getParameter(ACTIVE));
        return ConfigManager.getProperty("page.productManagement");
    }
}
