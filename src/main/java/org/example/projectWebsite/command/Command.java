package org.example.projectWebsite.command;

import org.example.projectWebsite.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request) throws CommandException;
}
