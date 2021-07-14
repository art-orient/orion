package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.AccessoriesCommand;
import com.art.orion.controller.command.impl.AddProductCommand;
import com.art.orion.controller.command.impl.AddProductPageCommand;
import com.art.orion.controller.command.impl.CartCommand;
import com.art.orion.controller.command.impl.CheckRegStatusCommand;
import com.art.orion.controller.command.impl.ClothingCommand;
import com.art.orion.controller.command.impl.HomeCommand;
import com.art.orion.controller.command.impl.LanguageCommand;
import com.art.orion.controller.command.impl.LoginCommand;
import com.art.orion.controller.command.impl.LoginUserCommand;
import com.art.orion.controller.command.impl.LogoutCommand;
import com.art.orion.controller.command.impl.MakeOrderCommand;
import com.art.orion.controller.command.impl.OrdersCommand;
import com.art.orion.controller.command.impl.ProductManagementCommand;
import com.art.orion.controller.command.impl.ProfileCommand;
import com.art.orion.controller.command.impl.RegisterUserCommand;
import com.art.orion.controller.command.impl.RegistrationCommand;
import com.art.orion.controller.command.impl.RemoveOrderCommand;
import com.art.orion.controller.command.impl.RemoveProductCommand;
import com.art.orion.controller.command.impl.SaleCommand;
import com.art.orion.controller.command.impl.SaveProductCommand;
import com.art.orion.controller.command.impl.ShoesCommand;
import com.art.orion.model.service.impl.UserServiceImpl;

public enum CommandType {
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTER_USER(new RegisterUserCommand(new UserServiceImpl())),
    CHECK_REG_STATUS(new CheckRegStatusCommand()),
    LOGIN(new LoginCommand()),
    LOGIN_USER(new LoginUserCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    PRODUCT_MANAGEMENT(new ProductManagementCommand()),
    ADD_PRODUCT_PAGE(new AddProductPageCommand()),
    SAVE_PRODUCT(new SaveProductCommand()),
    ACCESSORIES(new AccessoriesCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    CART(new CartCommand()),
    REMOVE_PRODUCT(new RemoveProductCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    CLOTHING(new ClothingCommand()),
    SHOES(new ShoesCommand()),
    SALE(new SaleCommand()),
    PROFILE(new ProfileCommand(new UserServiceImpl())),
    ORDERS(new OrdersCommand()),
    REMOVE_ORDER(new RemoveOrderCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}