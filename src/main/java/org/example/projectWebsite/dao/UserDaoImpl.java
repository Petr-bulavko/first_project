package org.example.projectWebsite.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.projectWebsite.dbconnection.ConnectionPool;
import org.example.projectWebsite.dbconnection.ProxyConnection;
import org.example.projectWebsite.exception.ConnectionPoolException;
import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.model.User;
import org.example.projectWebsite.model.UserData;
import org.example.projectWebsite.model.UserRole;
import org.example.projectWebsite.model.UserWithoutPassword;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.projectWebsite.exception.ExceptionMessage.*;

@SuppressWarnings("Duplicates")
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return instance;
    }
    private UserDaoImpl() {
    }

    private static final String SQL_SAVE_USER = "INSERT INTO user_account (login, password, role)" +
            " VALUES (?, ?, ?)";
    private static final String SQL_FIND_ALL_USERS = "SELECT id, login, role FROM user_account";
    private static final String SQL_FIND_USER_BY_LOGIN = SQL_FIND_ALL_USERS + " WHERE login = ?";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = SQL_FIND_ALL_USERS + " WHERE login = ? AND password = ?";
    private static final String SQL_FIND_DATA_BY_EMAIL = "SELECT id, user_id, first_name, last_name, date_of_birth, email " +
            "FROM user_data WHERE email = ?";
    private static final String SQL_SAVE_USER_DATA = "INSERT INTO " +
            "user_data (user_id, first_name, last_name, date_of_birth, email) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_DATA_BY_USER_ID = "SELECT id, user_id, first_name, last_name, date_of_birth, email" +
            " FROM user_data WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_DATA = "UPDATE user_data " +
            "SET first_name = ?, last_name = ?, date_of_birth = ?, email = ? WHERE user_id = ?";

    private static final String SQL_FIND_USER_WITHOUT_PASSWORD_BY_ID =
            "SELECT id, login, role FROM user_account WHERE id = ?";
    private static final String SQL_DELETE_ADMIN = "DELETE FROM user_account WHERE id = ?";

    private static final String SQL_DELETE_USER_FROM_USER_DATA = "DELETE FROM user_data WHERE user_id = ?";

    private static final String SQL_DELETE_USER_FROM_USER_ACCOUNT = "DELETE FROM user_account WHERE id = ?";

    private static final String SQL_UPDATE_USER = "";

    @Override
    public UserWithoutPassword saveUser(User entity) throws DaoException {
        UserWithoutPassword user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getRole().ordinal());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user = buildUserDTO(entity, generatedKeys);

            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public UserWithoutPassword saveUser(User entity, UserData userData) throws DaoException {
        UserWithoutPassword user = null;
        ProxyConnection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement userDataStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            userStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, entity.getLogin());
            userStatement.setString(2, entity.getPassword());
            userStatement.setInt(3, entity.getRole().ordinal());
            userStatement.executeUpdate();

            ResultSet generatedKeys = userStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user = buildUserDTO(entity, generatedKeys);
            }

            userDataStatement = connection.prepareStatement(SQL_SAVE_USER_DATA);
            assert user != null;
            userDataStatement.setLong(1, user.getUserId());
            userDataStatement.setString(2, userData.getFirstName());
            userDataStatement.setString(3, userData.getLastName());
            userDataStatement.setDate(4, Date.valueOf(userData.getDateOfBirth()));
            userDataStatement.setString(5, userData.getEmail());
            userDataStatement.executeUpdate();

            connection.commit();
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(TRANSACTION_EXCEPTION_MESSAGE, e1);
            }
        } finally {
            closeStatement(userStatement);
            closeStatement(userDataStatement);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<UserData> findDataByEmail(String email) throws DaoException {
        Optional<UserData> userData = Optional.empty();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_DATA_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userData = Optional.of(buildUserData(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return userData;
    }

    @Override
    public Optional<UserData> findDataByUserId(Long userId) throws DaoException {
        Optional<UserData> data = Optional.empty();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_DATA_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                data = Optional.of(buildUserData(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return data;
    }


    @Override
    public Optional<UserWithoutPassword> findById(Long id) throws DaoException {
        Optional<UserWithoutPassword> maybeUser = Optional.empty();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_WITHOUT_PASSWORD_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                maybeUser = Optional.of(buildUserDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return maybeUser;
    }

    @Override
    public boolean delete(Long entityId) throws DaoException {
        boolean result = false;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ADMIN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, entityId);
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
    public List<UserWithoutPassword> findAll() throws DaoException {
        ProxyConnection connection = null;
        Statement statement = null;
        List<UserWithoutPassword> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                UserWithoutPassword user = buildUserDTO(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return userList;
    }

    @Override
    public Optional<UserWithoutPassword> findByEntityName(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Optional<UserWithoutPassword> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(buildUserDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public User update(User entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return entity;
    }

    @Override
    public Optional<UserWithoutPassword> findUserByLoginAndPassword(String login, String password) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Optional<UserWithoutPassword> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(buildUserDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public boolean updateUserData(UserWithoutPassword user, UserData userData) throws DaoException {
        boolean result = false;
        ProxyConnection connection = null;
        PreparedStatement userDataPreparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            userDataPreparedStatement = connection.prepareStatement(SQL_UPDATE_USER_DATA);
            userDataPreparedStatement.setString(1, userData.getFirstName());
            userDataPreparedStatement.setString(2, userData.getLastName());
            userDataPreparedStatement.setDate(3, Date.valueOf(userData.getDateOfBirth()));
            userDataPreparedStatement.setString(4, userData.getEmail());
            userDataPreparedStatement.setLong(5, user.getUserId());
            userDataPreparedStatement.executeUpdate();

            connection.commit();
            result = true;
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(TRANSACTION_EXCEPTION_MESSAGE, e1);
            }
        } finally {
            closeStatement(userDataPreparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean deleteUser(Long userId) throws DaoException {
        boolean result = false;
        ProxyConnection connection = null;
        PreparedStatement userPreparedStatement = null;
        PreparedStatement userDataPreparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            userDataPreparedStatement = connection.prepareStatement(SQL_DELETE_USER_FROM_USER_DATA);
            userDataPreparedStatement.setLong(1, userId);
            userDataPreparedStatement.executeUpdate();

            userPreparedStatement = connection.prepareStatement(SQL_DELETE_USER_FROM_USER_ACCOUNT);
            userPreparedStatement.setLong(1, userId);
            userPreparedStatement.executeUpdate();

            connection.commit();
            result = true;
        } catch (ConnectionPoolException e) {
            throw new DaoException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(TRANSACTION_EXCEPTION_MESSAGE, e1);
            }
        } finally {
            closeStatement(userDataPreparedStatement);
            closeStatement(userPreparedStatement);
            closeConnection(connection);
        }
        return result;
    }


    private UserWithoutPassword buildUserDTO(User entity, ResultSet generatedKeys) throws SQLException {
        return UserWithoutPassword.builder()
                .userId(generatedKeys.getLong(1))
                .login(entity.getLogin())
                .role(entity.getRole())
                .build();
    }

    private UserWithoutPassword buildUserDTO(ResultSet resultSet) throws SQLException {
        return UserWithoutPassword.builder()
                .userId(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .role(UserRole.values()[(resultSet.getInt("role"))])
                .build();
    }

    private UserData buildUserData(ResultSet resultSet) throws SQLException {
        return UserData.builder()
                .userDataId(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .dateOfBirth(resultSet.getDate("date_of_birth").toLocalDate())
                .email(resultSet.getString("email"))
                .build();
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
}
