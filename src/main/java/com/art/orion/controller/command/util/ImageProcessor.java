package com.art.orion.controller.command.util;

import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.IMAGE;

public class ImageProcessor {
    private static final Logger logger = LogManager.getLogger();
    private static final char EXTENSION_SEPARATOR = '.';
    private static final char DIR_SEPARATOR = '/';
    private static final char DASH = '-';
    private static final char SPACE = ' ';
    private static final String NO_IMAGE = "no image";

    private ImageProcessor() {
    }

    public static String uploadImage(HttpServletRequest req, String brand, String modelName) {
        String newFilename = generateFilename(req, brand, modelName);
        String category = (String) req.getSession().getAttribute(CATEGORY) + DIR_SEPARATOR;
        logger.log(Level.DEBUG, () -> String.format("image path = %s%s", category, newFilename));
        try {
            return uploadFile(req.getPart(IMAGE), category, newFilename);
        } catch (IOException | ServletException e) {
            logger.log(Level.ERROR, "Image not saved", e);
            return NO_IMAGE;
        }
    }

    private static String generateFilename(HttpServletRequest req, String brand, String modelName) {
        String sourceName;
        String extension = "";
        try {
            sourceName = req.getPart(IMAGE).getSubmittedFileName();
            int index = sourceName.lastIndexOf(EXTENSION_SEPARATOR);
            extension = sourceName.substring(index);
        } catch (IOException | ServletException e) {
            logger.log(Level.ERROR, "file of image not found", e);
        }
        modelName = modelName.replace(DIR_SEPARATOR, DASH);
        return (brand + EXTENSION_SEPARATOR + modelName + extension).replace(SPACE, EXTENSION_SEPARATOR);
    }

    private static String uploadFile(Part part, String category, String fileName) {
        String uploadDir = ConfigManager.getProperty("dir.uploads") + category;
        try {
            Path path = Paths.get(uploadDir);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
            part.write(uploadDir + fileName);
            return fileName;
        } catch (IOException e) {
            logger.log(Level.ERROR, "Image not saved", e);
            return NO_IMAGE;
        }
    }
}
