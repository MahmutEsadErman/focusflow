package org.focusflow.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private LocalDateTime created_at;
    private LocalDateTime last_login;
}
