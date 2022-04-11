package org.example.projectWebsite.command.impl;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.exception.CommandException;
import org.example.projectWebsite.exception.ExceptionMessage;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.manager.PageManager;
import org.example.projectWebsite.manager.PageMappingConstant;
import org.example.projectWebsite.model.Film;
import org.example.projectWebsite.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FindFilmByNameWithoutRegistrationCommand implements Command {
    private static final boolean FLAG = true;
    private static final String IF_SUCCESS_MESSAGE = "findFilmSuccess";
    private static final String IF_ERROR_MESSAGE = "findFilmFailed";
    private static final String FILM_NAME = "filmName";
    private static final String FILM = "film";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        Optional<Film> film;
        String filmName = request.getParameter(FILM_NAME);
        String page = PageManager.getPageURI(PageMappingConstant.FIND_FILM_BY_ID_FOR_SHOW_WITHOUT_REGISTRATION_USER);
        CommandResult commandResult = new CommandResult(page, NavigationType.FORWARD);
        try {
            film = FilmService.getInstance().findFilmByName(filmName);
            if (film.isPresent()) {
                request.setAttribute(FILM, film);
                request.getSession().setAttribute(IF_SUCCESS_MESSAGE, FLAG);
            } else {
                page = PageManager.getPageURI(PageMappingConstant.START_PAGE);
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
                commandResult = new CommandResult(page, NavigationType.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
