package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.HomeCommand;
import com.art.orion.controller.command.impl.LanguageCommand;
import com.art.orion.controller.command.impl.RegisterUserCommand;
import com.art.orion.controller.command.impl.RegistrationCommand;

public enum TypeCommand {
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTER_USER(new RegisterUserCommand());
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
