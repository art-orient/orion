package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.*;

public enum TypeCommand {
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    CHECK_REG_STATUS(new CheckRegStatusCommand()),
    LOGIN(new LoginCommand()),
    LOGIN_USER(new LoginUserCommand()),
    LOGOUT(new LogoutCommand()),
    PRODUCT_MANAGEMENT(new ProductManagementCommand()),
    ADD_PRODUCT_PAGE(new AddProductPageCommand()),
    SAVE_PRODUCT(new SaveProductCommand()),
    ACCESSORIES(new AccessoriesCommand());

    private final Command command;

    TypeCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
