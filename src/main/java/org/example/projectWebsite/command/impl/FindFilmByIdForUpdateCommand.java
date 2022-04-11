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

public class FindFilmByIdForUpdateCommand implements Command {
    private static final boolean FLAG = true;
    private static final String IF_SUCCESS_MESSAGE = "findFilmSuccess";
    private static final String IF_ERROR_MESSAGE = "findFilmFailed";
    private static final String FILM_ID = "filmId";
    private static final String FILM = "film";
    private static final String SESSION_FILM_ID = "sessionFilmId";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        Optional<Film> film;
        Long filmId = Long.valueOf(request.getParameter(FILM_ID));
        String page = PageManager.getPageURI(PageMappingConstant.FIND_FILM_BY_ID_FOR_UPDATE);
        CommandResult commandResult = new CommandResult(page, NavigationType.FORWARD);
        try {
            film = FilmService.getInstance().findFilmById(filmId);
            if (film.isPresent()) {
                request.setAttribute(FILM, film);
                request.getSession().setAttribute(SESSION_FILM_ID,filmId);
                request.getSession().setAttribute(IF_SUCCESS_MESSAGE, FLAG);
            } else {
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
