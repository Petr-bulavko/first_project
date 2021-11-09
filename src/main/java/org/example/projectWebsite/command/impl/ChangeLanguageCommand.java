package org.example.projectWebsite.command.impl;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.exception.CommandException;
import org.example.projectWebsite.manager.PageManager;
import org.example.projectWebsite.manager.PageMappingConstant;
import org.example.projectWebsite.model.UserRole;
import org.example.projectWebsite.model.UserWithoutPassword;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    private static final String SESSION_USER = "user";
    private static final String CONTROLLER_URL = "http://localhost:8082/mainController";
    private static final String LANGUAGE = "language";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String controllerPage = CONTROLLER_URL;
        String referer = request.getHeader("referer");
        String page = "";
        NavigationType navigationType = NavigationType.FORWARD;
        UserWithoutPassword user = (UserWithoutPassword) request.getSession().getAttribute(SESSION_USER);
        if (controllerPage.equals(referer)) {
            if (user.getRole() == UserRole.USER) {
                page = PageManager.getPageURI(PageMappingConstant.USER_MAIN_PAGE_KEY);
            } else if (user.getRole() == UserRole.ADMIN) {
                page = PageManager.getPageURI(PageMappingConstant.ADMIN_MAIN_PAGE_KEY);
            }
        } else {
            page = referer;
            navigationType = NavigationType.REDIRECT;
        }
        String language = request.getParameter(LANGUAGE);
        request.setAttribute(LANGUAGE, language);
        return new CommandResult(page, navigationType);
    }
}
