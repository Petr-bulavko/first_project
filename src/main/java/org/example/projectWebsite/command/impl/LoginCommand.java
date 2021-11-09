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
import org.example.projectWebsite.model.UserRole;
import org.example.projectWebsite.model.UserWithoutPassword;
import org.example.projectWebsite.service.UserService;
import org.example.projectWebsite.util.PasswordEncoder;
import org.example.projectWebsite.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SESSION_USER = "user";
    private static final String SESSION_USER_DATA = "userData";
    private static final boolean FLAG = true;
    private static final String IF_ERROR_MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CommandResult commandResult = null;
        String page;
        String loginValue = request.getParameter(LOGIN);
        String passwordValue = request.getParameter(PASSWORD);
        try {
            if (UserValidator.isValidLogin(loginValue) &&
                    UserValidator.isValidPassword(passwordValue) &&
                    UserService.getInstance().isUserExist(loginValue, PasswordEncoder.encodePassword(passwordValue))) {

                Optional<UserRole> optionalUserRole = UserService.getInstance().getUserRoleByLogin(loginValue);
                Optional<UserWithoutPassword> userWithoutPassword = UserService.getInstance()
                        .findUserDTOByLoginAndPassword(loginValue, PasswordEncoder.encodePassword(passwordValue));

                if (optionalUserRole.isPresent() && userWithoutPassword.isPresent()) {
                    UserWithoutPassword user = userWithoutPassword.get();
                    UserRole userRole = optionalUserRole.get();
                    request.getSession().setAttribute(SESSION_USER, user);

                    switch (userRole) {
                        case USER:
                            page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.USER_MAIN_PAGE_AFTER_LOGIN_OR_REGISTRATION);
                            Optional<UserData> maybeUserData = UserService.getInstance().findUserDataByUserId(user.getUserId());
                            if (maybeUserData.isPresent()) {
                                request.getSession().setAttribute(SESSION_USER_DATA, maybeUserData.get());
                            }
                            break;
                        case ADMIN:
                            page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.ADMIN_MAIN_PAGE_KEY);
                            break;
                        default:
                            throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
                    }
                    commandResult = new CommandResult(page, NavigationType.REDIRECT);
                }
            } else {
                page = PageManager.getPageURI(PageMappingConstant.START_PAGE);
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
                commandResult = new CommandResult(page, NavigationType.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

}
