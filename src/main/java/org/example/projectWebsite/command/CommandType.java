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
    GO_TO_ADD_ADMIN(new GoToAddAdminCommand()),
    DESCRIPTION(new AllFilmsAdminCommand()),
    ALL_USERS(new AllUsersCommand()),
    DELETE_FILM(new DeleteFilmCommand()),
    UPDATE_FILM(new UpdateFilmCommand()),
    FIND_FILM_FOR_SHOW_WITHOUT_REGISTRATION(new FindFilmByIdForShowWithoutRegistrationUserCommand()),
    FIND_FILM_FOR_SHOW_AUTHORIZED(new FindFilmByIdForShowAuthorizedUserCommand()),
    FIND_FILM_BY_NAME_WITHOUT_REGISTRATION(new FindFilmByNameWithoutRegistrationCommand()),
    FIND_FILM_BY_NAME_AUTHORIZED(new FindFilmByNameAuthorizedCommand()),
    FIND_FILM_FOR_UPDATE(new FindFilmByIdForUpdateCommand()),
    ALL_FILMS_USER(new AllFilmsUserCommand()),
    ALL_FILMS(new AllFilmsAdminCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
