package dao;

import model.Film;
import sql.JDBCConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilmBasicDaoImp implements BasicDao<Film> {

    @Override
    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();
        try (Connection conn = JDBCConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from films");) {
            //Получем все элементы таблицы
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String filmName = resultSet.getString(2);
                int filmYear = resultSet.getInt(3);
                String filmGenre = resultSet.getString(4);
                String filmCountry = resultSet.getString(5);
                Film film = new Film(id, filmName, filmYear, filmGenre, filmCountry);
                films.add(film);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return films;
    }

    @Override
    public Film getById(int id) {
        Film film = new Film();
        try (Connection conn = JDBCConnector.getConnection()) {
            String sql = "select * from films where id=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int filmId = resultSet.getInt(1);
                    String filmName = resultSet.getString(2);
                    int filmYear = resultSet.getInt(3);
                    String filmGenre = resultSet.getString(4);
                    String filmCountry = resultSet.getString(5);
                    film = new Film(filmId, filmName, filmYear, filmGenre, filmCountry);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return film;
    }

    @Override
    public Film deleteById(int id) {
        Film film = getById(id);
        try (Connection conn = JDBCConnector.getConnection()) {
            String sql = "DELETE FROM films WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return film;
    }

    @Override
    public Film updateById(Film model) {
        Film film = getById(model.getId());
        try (Connection conn = JDBCConnector.getConnection()) {
            String sql = "UPDATE films SET filmName = ?, filmYear = ?, filmGenre = ?, filmCountry = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, model.getFilmName());
                preparedStatement.setInt(2, model.getFilmYear());
                preparedStatement.setString(3, model.getFilmGenre());
                preparedStatement.setString(4, model.getFilmCountry());
                preparedStatement.setInt(5, film.getId());
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return film;
    }

    @Override
    public Film create(Film model) {
        try (Connection conn = JDBCConnector.getConnection()) {
            String sql = "insert into films (filmName, filmYear, filmGenre, filmCountry) values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, model.getFilmName());
                preparedStatement.setInt(2, model.getFilmYear());
                preparedStatement.setString(3, model.getFilmGenre());
                preparedStatement.setString(4, model.getFilmCountry());
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model;
    }
}
