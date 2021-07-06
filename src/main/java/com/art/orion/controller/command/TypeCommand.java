package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.AccessoriesCommand;
import com.art.orion.controller.command.impl.AddProductCommand;
import com.art.orion.controller.command.impl.AddProductPageCommand;
import com.art.orion.controller.command.impl.CartCommand;
import com.art.orion.controller.command.impl.CheckRegStatusCommand;
import com.art.orion.controller.command.impl.HomeCommand;
import com.art.orion.controller.command.impl.LanguageCommand;
import com.art.orion.controller.command.impl.LoginCommand;
import com.art.orion.controller.command.impl.LoginUserCommand;
import com.art.orion.controller.command.impl.LogoutCommand;
import com.art.orion.controller.command.impl.ProductManagementCommand;
import com.art.orion.controller.command.impl.RegisterUserCommand;
import com.art.orion.controller.command.impl.RegistrationCommand;
import com.art.orion.controller.command.impl.SaveProductCommand;

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
    ACCESSORIES(new AccessoriesCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    CART(new CartCommand());

    private final Command command;

    TypeCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
