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
import java.util.List;

public class AllFilmsUserCommand implements Command {
    private static final String ALL_FILMS = "allFilms";
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        List<Film> allFilms;
        String page = PageManager.getPageURI(PageMappingConstant.USER_WITHOUT_REGISTRATION_ALL_FILMS_PAGE_KEY);
        CommandResult commandResult = new CommandResult(page, NavigationType.FORWARD);
        try {
            allFilms = FilmService.getInstance().findAllFilms();
            request.setAttribute(ALL_FILMS, allFilms);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
