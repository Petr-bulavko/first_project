package org.example.projectWebsite.dao;

import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.model.User;
import org.example.projectWebsite.model.UserData;
import org.example.projectWebsite.model.UserWithoutPassword;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    /**
     * Save new admin user in database
     *
     * @param entity its a new admin entity
     * @return {@code UserWithoutPassword} for saving into {@code HttpSession}
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    UserWithoutPassword saveUser(User entity) throws DaoException;

    /**
     * Save new user in database with {@code UserData}
     *
     * @param entity      is a new {@code User} entity
     * @param userData    is a new {@code UserData}
     * @return {@code UserWithoutPassword} for saving into {@code HttpSession}
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    UserWithoutPassword saveUser(User entity, UserData userData) throws DaoException;

    /**
     * Find in database {@code UserData} by email value
     *
     * @param email its {@code String} value of the email
     * @return {@code Optional} of {@code UserData} if value present
     * or empty {@code Optional}
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    Optional<UserData> findDataByEmail(String email) throws DaoException;

    /**
     * Find in database {@code UserData} by user's id
     *
     * @param userId is id of the user whose {@code UserData}
     *               need to find
     * @return {@code Optional} of {@code UserData} if value present
     * or empty {@code Optional}
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    Optional<UserData> findDataByUserId(Long userId) throws DaoException;

    /**
     * Check in database {@code User} by his login and password value
     *
     * @param login    its a {@code String} login value
     * @param password its a {@code String} encoding password value
     * @return {@code Optional} of {@code UserWithoutPassword} if value present
     * or empty {@code Optional}
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    Optional<UserWithoutPassword> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Update in database user's data
     *
     * @param user        its a user whose data will be updated
     * @param userData    its a new {@code UserData}
     * @return {@code true} if success or {@code false} if failed
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    boolean updateUserData(UserWithoutPassword user, UserData userData) throws DaoException;

    /**
     * Delete any user from database
     *
     * @param userId is id of the user which need to delete
     * @return {@code true} if success or {@code false} if failed
     * @throws DaoException if have {@code ConnectionPool} error or
     *                      any database error
     */
    boolean deleteUser(Long userId) throws DaoException;
}
