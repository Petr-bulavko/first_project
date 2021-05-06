package org.example.projectWebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Entity{
    private Long userId;
    private String login;
    private String mail;
    private String password;
    private UserRole role;
}
