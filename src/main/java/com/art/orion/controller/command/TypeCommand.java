package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.*;

public enum TypeCommand {
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    CHECK_REG_STATUS(new CheckRegStatusCommand());
//    LOGIN(new LoginCommand()),
//    LOGOUT(new LogoutCommand()),

    private final Command command;

    TypeCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
