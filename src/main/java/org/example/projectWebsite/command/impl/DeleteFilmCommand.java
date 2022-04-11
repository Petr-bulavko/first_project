package org.example.projectWebsite.command.impl;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.exception.CommandException;
import org.example.projectWebsite.exception.ExceptionMessage;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.manager.PageManager;
import org.example.projectWebsite.manager.PageMappingConstant;
import org.example.projectWebsite.service.FilmService;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("Duplicates")
public class DeleteFilmCommand implements Command {
    private static final boolean FLAG = true;
    private static final String IF_SUCCESS_MESSAGE = "filmDeleteSuccess";
    private static final String IF_ERROR_MESSAGE = "filmDeleteFailed";
    private static final String FILM_ID = "filmId";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.ADMIN_ALL_FILMS_PAGE_KEY);
        CommandResult commandResult = new CommandResult(page, NavigationType.REDIRECT);
        Long filmId = Long.valueOf(request.getParameter(FILM_ID));
        try {
            if (FilmService.getInstance().deleteFilm(filmId)) {
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
