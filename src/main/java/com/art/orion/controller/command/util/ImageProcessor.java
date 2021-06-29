package com.art.orion.controller.command.util;

import com.art.orion.util.ConfigManager;
import com.art.orion.util.Constant;
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

import static com.art.orion.util.Constant.IMAGE;

public class ImageProcessor {
    private static final Logger logger = LogManager.getLogger();
    private static final String EXTENSION_SEPARATOR = ".";

    private ImageProcessor() {
    }

    public static String uploadImage(HttpServletRequest req, String brand, String modelName) {
        String filename = generateFilename(req, brand, modelName);
        System.out.println("filename - " + filename);

        try {
            return uploadFile(req.getPart(IMAGE), filename);
        } catch (IOException | ServletException e) {
//            e.printStackTrace();
            return null;
        }
    }

    private static String generateFilename(HttpServletRequest req, String brand, String modelName) {
        String sourceName = null;
        try {
            sourceName = req.getPart(IMAGE).getSubmittedFileName();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        System.out.println("sourceName - " + sourceName);
        int index = sourceName.lastIndexOf(EXTENSION_SEPARATOR);
        String extension = sourceName.substring(index);
        String category = (String) req.getSession().getAttribute("category");
        System.out.println("category "+ category);
        String filename = new StringBuilder(category)
                .append("/")
                .append(brand)
                .append("_")
                .append(modelName)
                .append(extension)
                .toString();
        System.out.println("filename result = " + filename);
        return filename;
    }

    private static String uploadFile(Part part, String fileName) {
        String uploadDir = ConfigManager.getProperty("dir.uploads");
        try {
            Path path = Paths.get(uploadDir);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
            part.write(uploadDir + fileName);
            return fileName;
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            return null;
        }
    }
}
