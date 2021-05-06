package org.example.projectWebsite.command.impl;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.exception.CommandException;
import org.example.projectWebsite.exception.ExceptionMessage;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.manager.PageManager;
import org.example.projectWebsite.manager.PageMappingConstant;
import org.example.projectWebsite.model.UserData;
import org.example.projectWebsite.model.UserWithoutPassword;
import org.example.projectWebsite.service.UserService;
import org.example.projectWebsite.util.PasswordEncoder;
import org.example.projectWebsite.validator.UserDataValidator;
import org.example.projectWebsite.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@SuppressWarnings("Duplicates")
public class UpdateUserCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String BIRTHDAY = "birthday";
    private static final String SESSION_USER = "user";
    private static final String SESSION_USER_DATA = "userData";
    private static final boolean FLAG = true;
    private static final String IF_ERROR_MESSAGE = "updatedFailed";
    private static final String IF_SUCCESS_MESSAGE = "updatedSuccess";


    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.USER_MAIN_PAGE_AFTER_LOGIN_OR_REGISTRATION);
        CommandResult commandResult = new CommandResult(page, NavigationType.REDIRECT);
        String loginValue = request.getParameter(LOGIN);
        String passwordValue = request.getParameter(PASSWORD);
        String firstNameValue = request.getParameter(FIRST_NAME);
        String lastNameValue = request.getParameter(LAST_NAME);
        String emailValue = request.getParameter(EMAIL);
        String birthdayValue = request.getParameter(BIRTHDAY);

        UserWithoutPassword user = (UserWithoutPassword) request.getSession().getAttribute(SESSION_USER);
        UserData sessionUserData = (UserData) request.getSession().getAttribute(SESSION_USER_DATA);

        try {
            if (UserValidator.isValidLogin(loginValue) &&
                    UserValidator.isValidPassword(passwordValue) &&
                    UserDataValidator.isValidFirstName(firstNameValue) &&
                    UserDataValidator.isValidLastName(lastNameValue) &&
                    UserDataValidator.isValidEmail(emailValue) &&
                    UserDataValidator.isValidBirthday(birthdayValue) &&
                    UserService.getInstance().isUserExist(loginValue, PasswordEncoder.encodePassword(passwordValue)) &&
                    (!UserService.getInstance().isEmailExist(emailValue) || sessionUserData.getEmail().equals(emailValue))) {
                UserData updatedUserData = UserService.getInstance()
                        .makeUserData(firstNameValue, lastNameValue, emailValue, LocalDate.parse(birthdayValue));
                if (UserService.getInstance().updateUserData(user, updatedUserData)) {
                    request.getSession().setAttribute(SESSION_USER_DATA, updatedUserData);
                    request.getSession().setAttribute(IF_SUCCESS_MESSAGE, FLAG);
                }
            } else {
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
