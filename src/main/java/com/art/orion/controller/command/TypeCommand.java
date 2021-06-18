package com.art.orion.controller.command;

import com.art.orion.controller.command.impl.LanguageCommand;
import com.art.orion.controller.command.impl.RegistrationCommand;

public enum TypeCommand {
    LANGUAGE(new LanguageCommand()),
    REGISTRATION(new RegistrationCommand());
//    LOGIN(new LoginCommand()),
//    LOGOUT(new LogoutCommand()),
//    HOME(new HomeCommand())

    private final Command command;

    TypeCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
