package org.focusflow.controller.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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

    @NotBlank(message="Status is required")
    @JsonProperty("status")
    private String status;

    @NotNull(message = "Due date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("dueDate")
    private LocalDateTime dueDate;

}
