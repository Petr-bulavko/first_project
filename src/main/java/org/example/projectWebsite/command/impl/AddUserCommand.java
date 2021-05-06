package org.example.projectWebsite.command.impl;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.exception.CommandException;
import org.example.projectWebsite.exception.ExceptionMessage;
import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.manager.PageManager;
import org.example.projectWebsite.manager.PageMappingConstant;
import org.example.projectWebsite.model.UserRole;
import org.example.projectWebsite.service.UserService;
import org.example.projectWebsite.util.PasswordEncoder;
import org.example.projectWebsite.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("Duplicates")
public class AddUserCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final boolean FLAG = true;
    private static final String IF_SUCCESS_MESSAGE = "userSavedSuccess";
    private static final String IF_ERROR_MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.ADMIN_MAIN_PAGE_KEY);
        CommandResult commandResult = new CommandResult(page, NavigationType.REDIRECT);
        UserRole role = UserRole.valueOf(request.getParameter(ROLE));
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            if (UserValidator.isValidLogin(login) &&
                    UserValidator.isValidPassword(password) &&
                    !UserService.getInstance().isUsernameExist(login)) {
                UserService.getInstance().saveUser(login, PasswordEncoder.encodePassword(password), role);
                request.getSession().setAttribute(IF_SUCCESS_MESSAGE, FLAG);
            } else {
                request.getSession().setAttribute(IF_ERROR_MESSAGE, FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
