package org.example.projectWebsite.service;

import org.example.projectWebsite.dao.UserDaoImpl;
import org.example.projectWebsite.exception.DaoException;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.model.User;
import org.example.projectWebsite.model.UserData;
import org.example.projectWebsite.model.UserRole;
import org.example.projectWebsite.model.UserWithoutPassword;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.example.projectWebsite.exception.ExceptionMessage.SERVICE_EXCEPTION_MESSAGE;

public class UserService {
    private static UserService instance = new UserService();

    /**
     * Return instance of the {@code UserService}
     *
     * @return instance of the {@code UserService}
     */
    public static UserService getInstance() {
        return instance;
    }

    private UserService() {
    }

    /**
     * Save admin
     *
     * @param login    its a login value
     * @param password its a encoded password value
     * @param userRole its a admin role
     * @return {@code UserWithoutPassword} for saving into {@code HttpSession}
     * @throws ServiceException if Dao layer can't do own method
     */
    public UserWithoutPassword saveUser(String login, String password, UserRole userRole) throws ServiceException {
        UserWithoutPassword userWithoutPassword;
        try {
            User user = buildUser(login, password, userRole);
            userWithoutPassword = UserDaoImpl.getInstance().saveUser(user);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userWithoutPassword;
    }

    /**
     * Save user with data
     *
     * @param user        its a {@code User} object
     * @param userData    its a {@code UserData} object
     * @return {@code UserWithoutPassword} for saving into {@code HttpSession}
     * @throws ServiceException if Dao layer can't do own method
     */
    public UserWithoutPassword saveUser(User user, UserData userData) throws ServiceException {
        UserWithoutPassword userWithoutPassword;
        try {
            userWithoutPassword = UserDaoImpl.getInstance().saveUser(user, userData);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userWithoutPassword;
    }

    /**
     * Create {@code User} object from data
     *
     * @param login    its a login value
     * @param password its a encoded password value
     * @param userRole its a {@code UserRole} value
     * @return {@code User} object
     */
    public User makeUserFromData(String login, String password, UserRole userRole) {
        return buildUser(login, password, userRole);
    }

    /**
     * Create {@code UserData} object from data
     *
     * @param firstName its a first name value
     * @param lastName  its a last name value
     * @param email     its a email value
     * @param birthday  its a birthday value
     * @return {@code UserData} object
     */
    public UserData makeUserData(String firstName, String lastName, String email, LocalDate birthday) {
        return buildUserData(firstName, lastName, email, birthday);
    }

    /**
     * Check if exist in database this user
     *
     * @param login    its login value
     * @param password its a encoded password value
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean isUserExist(String login, String password) throws ServiceException {
        boolean result = false;
        try {
            Optional<UserWithoutPassword> maybeUser = UserDaoImpl.getInstance().findUserByLoginAndPassword(login, password);
            if (maybeUser.isPresent()) {
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    /**
     * Check if exist in database this email
     *
     * @param email its a email value
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean isEmailExist(String email) throws ServiceException {
        boolean result = false;
        try {
            Optional<UserData> dataByEmail = UserDaoImpl.getInstance().findDataByEmail(email);
            if (dataByEmail.isPresent()) {
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    /**
     * Check if exist in database this username (login)
     *
     * @param login its a login value
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean isUsernameExist(String login) throws ServiceException {
        boolean result = false;
        try {
            Optional<UserWithoutPassword> maybeUser = UserDaoImpl.getInstance().findByEntityName(login);
            if (maybeUser.isPresent()) {
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    /**
     * Find user in database by his login and password
     *
     * @param login    its a login value
     * @param password its a encoded password value
     * @return {@code UserWithoutPassword} for saving in {@code HttpSession}
     * @throws ServiceException if Dao layer can't do own method
     */
    public Optional<UserWithoutPassword> findUserDTOByLoginAndPassword(String login, String password) throws ServiceException {
        Optional<UserWithoutPassword> maybeUser;
        try {
            maybeUser = UserDaoImpl.getInstance().findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return maybeUser;
    }

    /**
     * Find in database {@code UserData} by user's id
     *
     * @param userId its id of the user which data need to find
     * @return {@code Optional} of {@code UserData} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Dao layer can't do own method
     */
    public Optional<UserData> findUserDataByUserId(Long userId) throws ServiceException {
        Optional<UserData> maybeData;
        try {
            maybeData = UserDaoImpl.getInstance().findDataByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return maybeData;
    }

    /**
     * Find in database user's role
     *
     * @param login its a login value
     * @return {@code Optional} of {@code UserRole} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Dao layer can't do own method
     */
    public Optional<UserRole> getUserRoleByLogin(String login) throws ServiceException {
        Optional<UserRole> userRole = Optional.empty();
        try {
            Optional<UserWithoutPassword> user = UserDaoImpl.getInstance().findByEntityName(login);
            if (user.isPresent()) {
                userRole = Optional.of(user.get().getRole());
            }
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userRole;
    }

    /**
     * Update user's data
     *
     * @param user        its a {@code UserWithoutPassword} object which data will be updated
     * @param userData    its a new {@code UserData}
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean updateUserData(UserWithoutPassword user, UserData userData) throws ServiceException {
        boolean result;
        try {
            result = UserDaoImpl.getInstance().updateUserData(user, userData);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    /**
     * Find {@code UserWithoutPassword} by his id
     *
     * @param userId its a user id which need to find
     * @return {@code Optional} of {@code UserWithoutPassword} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Dao layer can't do own method
     */
    public Optional<UserWithoutPassword> findUserWithoutPasswordById(Long userId) throws ServiceException {
        Optional<UserWithoutPassword> maybeUser;
        try {
            maybeUser = UserDaoImpl.getInstance().findById(userId);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return maybeUser;
    }

    /**
     * Find all users in database
     *
     * @return {@code List} of the users
     * @throws ServiceException if Dao layer can't do own method
     */
    public List<UserWithoutPassword> findAllUsers() throws ServiceException {
        List<UserWithoutPassword> userList;
        try {
            userList = UserDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userList;
    }

    /**
     * Delete from database admin
     *
     * @param adminId its id of admin or doctor
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean deleteAdmin(Long adminId) throws ServiceException {
        boolean result;
        try {
            result = UserDaoImpl.getInstance().delete(adminId);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    /**
     * Delete user from database
     *
     * @param userId its iser's id which need to delete
     * @return {@code true} if success or {@code false} if failed
     * @throws ServiceException if Dao layer can't do own method
     */
    public boolean deleteUser(Long userId) throws ServiceException {
        boolean result;
        try {
            result = UserDaoImpl.getInstance().deleteUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    private UserData buildUserData(String firstName, String lastName, String email, LocalDate birthday) {
        return UserData.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .dateOfBirth(birthday)
                .build();
    }

    private User buildUser(String login, String password, UserRole userRole) {
        return User.builder()
                .login(login)
                .password(password)
                .role(userRole)
                .build();
    }
}
