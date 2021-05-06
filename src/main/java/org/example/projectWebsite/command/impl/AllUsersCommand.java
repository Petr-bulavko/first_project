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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AllUsersCommand implements Command {
    private static final String ALL_USERS_MAP = "allUsersMap";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String page = request.getContextPath() + PageManager.getPageURI(PageMappingConstant.ADMIN_ALL_USERS_PAGE_KEY);
        CommandResult commandResult = new CommandResult(page, NavigationType.FORWARD);

        Map<UserWithoutPassword, UserData> allUsersMap = new HashMap<>();
        try {
            List<UserWithoutPassword> allUsers = UserService.getInstance().findAllUsers();
            for (UserWithoutPassword user : allUsers) {
                if (UserRole.USER == user.getRole()) {
                    Optional<UserData> optionalUserData = UserService.getInstance().findUserDataByUserId(user.getUserId());
                    if (optionalUserData.isPresent()) {
                        UserData userData = optionalUserData.get();
                        allUsersMap.put(user, userData);
                    }
                } else {
                    allUsersMap.put(user, UserData.builder().build());
                }
            }
            request.setAttribute(ALL_USERS_MAP, allUsersMap);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
