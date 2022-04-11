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
import org.example.projectWebsite.validator.FilmDataValidator;

import javax.servlet.http.HttpServletRequest;

public class UpdateFilmCommand implements Command {

    private static final String FILM_NAME = "filmName";
    private static final String FILM_YEAR = "filmYear";
    private static final String FILM_GENRE = "filmGenre";
    private static final String FILM_COUNTRY = "filmCountry";
    private static final String DESCRIPTION = "description";
    private static final String SESSION_FILM_DATA = "filmData";
    private static final boolean FLAG = true;
    private static final String IF_ERROR_MESSAGE = "updatedFailed";
    private static final String IF_SUCCESS_MESSAGE = "updatedSuccess";
    private static final String SESSION_FILM_ID = "sessionFilmId";
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        Long filmId = (Long) request.getSession().getAttribute(SESSION_FILM_ID);
        String page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.ADMIN_ALL_FILMS_PAGE_KEY);
        CommandResult commandResult = new CommandResult(page, NavigationType.REDIRECT);

        String filmNameValue = request.getParameter(FILM_NAME);
        String filmYearValue = request.getParameter(FILM_YEAR);
        String filmGenreValue = request.getParameter(FILM_GENRE);
        String filmCountryValue = request.getParameter(FILM_COUNTRY);
        String descriptionValue = request.getParameter(DESCRIPTION);

        try {
            if (FilmDataValidator.isValidFilmName(filmNameValue) &&
                    FilmDataValidator.isValidFilmDescription(descriptionValue)
            ) {
                Film updatedFilmData = FilmService.getInstance()
                        .makeFilmData(filmId, filmNameValue, filmYearValue, filmGenreValue, filmCountryValue, descriptionValue);
                if (FilmService.getInstance().updateFilmData(updatedFilmData)) {
                    request.getSession().setAttribute(SESSION_FILM_DATA, updatedFilmData);
                    request.getSession().setAttribute(IF_SUCCESS_MESSAGE, FLAG);
                }
            } else {
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
            }
            request.getSession().removeAttribute(SESSION_FILM_ID);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}