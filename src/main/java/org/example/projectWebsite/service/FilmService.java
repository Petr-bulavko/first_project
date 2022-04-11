package org.example.projectWebsite.service;

import org.example.projectWebsite.dao.FilmDaoImpl;
import org.example.projectWebsite.dao.UserDaoImpl;
import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.model.Film;
import org.example.projectWebsite.model.UserWithoutPassword;

import java.util.List;
import java.util.Optional;

import static org.example.projectWebsite.exception.ExceptionMessage.SERVICE_EXCEPTION_MESSAGE;

public class FilmService {
    private static FilmService instance = new FilmService();


    public static FilmService getInstance() {
        return instance;
    }

    public FilmService() {
    }

    public Film makeFilmData(Long filmId, String filmName, String filmYear, String filmGenre, String filmCountry, String description) {
        return buildFilmData(filmId, filmName, filmYear, filmGenre, filmCountry, description);
    }

    public List<Film> findAllFilms() throws ServiceException {
        List<Film> films;
        try {
            films = FilmDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return films;
    }

    public Optional<Film> findFilmByName(String filmName) throws ServiceException {
        Optional<Film> maybeFilm;
        try {
            maybeFilm = FilmDaoImpl.getInstance().findByEntityName(filmName);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return maybeFilm;
    }

    public Optional<Film> findFilmById(Long filmId) throws ServiceException {
        Optional<Film> maybeFilm;
        try {
            maybeFilm = FilmDaoImpl.getInstance().findById(filmId);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return maybeFilm;
    }

    public boolean deleteFilm(Long filmId) throws ServiceException {
        boolean result = false;
        try {
            if (FilmDaoImpl.getInstance().delete(filmId)) {
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    public boolean updateFilmData(Film film) throws ServiceException {
        boolean result;
        try {
            result = FilmDaoImpl.getInstance().updateFilm(film);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    private Film buildFilmData(Long filmId, String filmName, String filmYear, String filmGenre, String filmCountry, String description) {
        return Film.builder()
                .filmId(filmId)
                .filmName(filmName)
                .filmYear(filmYear)
                .filmGenre(filmGenre)
                .filmCountry(filmCountry)
                .description(description)
                .build();
    }
}
