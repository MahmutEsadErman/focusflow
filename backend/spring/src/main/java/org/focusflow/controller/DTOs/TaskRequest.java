package org.focusflow.controller.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    private String title;
    private String longDescription;
    private String shortDescription;
}
