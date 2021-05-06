package org.example.projectWebsite.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommandResult {
    private String page;
    private NavigationType navigationType;

}
