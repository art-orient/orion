package com.art.orion.util;

import java.util.ResourceBundle;

public class ConfigManager {
    protected static final String BUNDLE_NAME = "config";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
