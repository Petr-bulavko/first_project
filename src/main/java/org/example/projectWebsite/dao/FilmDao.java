package org.example.projectWebsite.dao;

import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.model.Film;

import java.util.List;

public interface FilmDao extends BaseDao<Film>{

    List<Film> findAll() throws DaoException;

    boolean delete(Long filmId) throws DaoException;

    boolean updateFilm(Film film) throws DaoException;
}
