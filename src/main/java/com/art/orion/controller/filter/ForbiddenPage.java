package com.art.orion.controller.filter;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenPage {
    private static final List<String> ADMIN_PAGES = new ArrayList<>(List.of(
            "product_management",
            "user_management",
            "order_management",
            "add_product_page",
            "save_product"));
    private static final List<String> AUTHENTICATED_USER_PAGES = new ArrayList<>(List.of("add_product",
            "profile", "card"));

    private ForbiddenPage() {
    }

    public static List<String> getAdminPages() {
        return ADMIN_PAGES;
    }

    public static List<String> getAuthenticatedUserPages() {
        return AUTHENTICATED_USER_PAGES;
    }
}
