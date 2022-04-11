package org.example.projectWebsite.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.projectWebsite.dbconnection.ConnectionPool;
import org.example.projectWebsite.dbconnection.ProxyConnection;
import org.example.projectWebsite.exception.ConnectionPoolException;
import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.model.Entity;
import org.example.projectWebsite.model.Film;
import org.example.projectWebsite.model.UserWithoutPassword;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.projectWebsite.exception.ExceptionMessage.*;

public class FilmDaoImpl implements FilmDao{
    private static FilmDaoImpl instance = new FilmDaoImpl();

    public static FilmDaoImpl getInstance() {
        return instance;
    }

    private FilmDaoImpl() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SQL_FIND_ALL_FILMS_DATA = "SELECT id, filmName, filmYear, filmGenre, filmCountry, description, img, linkMovie FROM films";
    private static final String SQL_DELETE_FILM_BY_ID = "DELETE FROM films WHERE id = ?";
    private static final String SQL_UPDATE_FILM_DATA = "UPDATE films " +
            "SET filmName = ?, filmYear = ?, filmGenre = ?, filmCountry = ?, description = ? WHERE id = ?";
    private static final String SQL_FIND_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    private static final String SQL_FIND_FILM_BY_NAME = "SELECT * FROM films WHERE filmName = ?";

    @Override
    public List<Film> findAll() throws DaoException {
        List<Film> filmList = new ArrayList<>();
        ProxyConnection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_FILMS_DATA);
            while (resultSet.next()) {
                Film film = buildFilm(resultSet);
                filmList.add(film);
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return filmList;
    }

    @Override
    public Optional<Film> findById(Long id) throws DaoException {
        Optional<Film> maybeFilm = Optional.empty();
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_FILM_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                maybeFilm = Optional.of(buildFilmDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return maybeFilm;
    }

    @Override
    public boolean delete(Long filmId) throws DaoException {
        boolean result = false;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_FILM_BY_ID, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, filmId);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Optional<Film> findByEntityName(String filmName) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Optional<Film> film = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_FILM_BY_NAME);
            preparedStatement.setString(1, filmName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = Optional.ofNullable(buildFilmDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return film;
    }

    @Override
    public Film update(Film entity) throws DaoException {
        return null;
    }

    @Override
    public boolean updateFilm(Film film) throws DaoException {
        boolean result = false;
        ProxyConnection connection = null;
        PreparedStatement filmDataPreparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            filmDataPreparedStatement = connection.prepareStatement(SQL_UPDATE_FILM_DATA);
            filmDataPreparedStatement.setString(1, film.getFilmName());
            filmDataPreparedStatement.setString(2, film.getFilmYear());
            filmDataPreparedStatement.setString(3, film.getFilmGenre());
            filmDataPreparedStatement.setString(4, film.getFilmCountry());
            filmDataPreparedStatement.setString(5, film.getDescription());
            filmDataPreparedStatement.setLong(6, film.getFilmId());
            filmDataPreparedStatement.executeUpdate();

            connection.commit();
            result = true;
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                throw new DaoException(TRANSACTION_EXCEPTION_MESSAGE, e1);
            }
        } finally {
            closeStatement(filmDataPreparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    private void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't close statement", e);
        }
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't close proxy connection", e);
        }
    }

    private Film buildFilm(ResultSet resultSet) throws SQLException {
        return Film.builder()
                .filmId(resultSet.getLong("id"))
                .filmName(resultSet.getString("filmName"))
                .filmYear(resultSet.getString("filmYear"))
                .filmGenre(resultSet.getString("filmGenre"))
                .filmCountry(resultSet.getString("filmCountry"))
                .description(resultSet.getString("description"))
                .img(resultSet.getString("img"))
                .build();

    }

    private Film buildFilmDTO(ResultSet resultSet) throws SQLException {
        return Film.builder()
                .filmId(resultSet.getLong("id"))
                .filmName(resultSet.getString("filmName"))
                .filmYear(resultSet.getString("filmYear"))
                .filmGenre(resultSet.getString("filmGenre"))
                .filmCountry(resultSet.getString("filmCountry"))
                .description(resultSet.getString("description"))
                .img(resultSet.getString("img"))
                .linkMovie(resultSet.getString("linkMovie"))
                .build();

    }
}
