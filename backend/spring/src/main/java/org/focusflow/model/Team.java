package org.focusflow.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Team {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created_at;

}
