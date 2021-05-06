package org.example.projectWebsite.command;

import org.example.projectWebsite.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    LOGOUT(new LogoutCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    GO_TO_ADD_USER(new GoToAddUserCommand()),
    ALL_USERS(new AllUsersCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
