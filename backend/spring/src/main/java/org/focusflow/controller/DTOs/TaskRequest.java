package org.focusflow.controller.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskRequest {
    @NotBlank(message = "Title is required")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Long description is required")
    @JsonProperty("longDescription")
    private String longDescription;

    @NotBlank(message = "Short description is required")
    @JsonProperty("shortDescription")
    private String shortDescription;
}
