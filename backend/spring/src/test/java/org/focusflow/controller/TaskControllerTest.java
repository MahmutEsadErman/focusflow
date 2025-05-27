package org.focusflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.focusflow.controller.DTOs.ApiResponse;
import org.focusflow.controller.DTOs.TaskRequest;
import org.focusflow.exception.TaskException;
import org.focusflow.model.Task;
import org.focusflow.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(TaskController.class)
@WithMockUser
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON strings

    private TaskRequest validTaskRequest;
    private Task task1;

    @BeforeEach
    void setUp() {
        validTaskRequest = new TaskRequest();
        validTaskRequest.setTitle("A Valid Task Title"); 
        validTaskRequest.setShortDescription("Valid short description");
        validTaskRequest.setLongDescription("Valid long description for the task");

        task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Test Task 1");
        task1.setShortDescription("Short desc 1");
        task1.setLongDescription("Long desc 1");
    }

    // --- Test createTask --- 

    @Test
    void createTask_whenValidRequest_shouldReturnCreated() throws Exception {
        // Mocking the service call. Arguments to createTask in controller are: title, longDescription, shortDescription
        doNothing().when(taskService).createTask(eq("A Valid Task Title"), eq("Valid long description for the task"), eq("Valid short description"));

        mockMvc.perform(post("/tasks/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validTaskRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is(HttpStatus.CREATED.value())))
                .andExpect(jsonPath("$.message", is("Task created successfully")));

        // Verifying the service call. Arguments order: title, longDescription, shortDescription
        verify(taskService, times(1)).createTask("A Valid Task Title", "Valid long description for the task", "Valid short description");
    }

    @Test
    void createTask_whenTitleMissing_shouldReturnBadRequest() throws Exception {
        TaskRequest invalidRequest = new TaskRequest();
        invalidRequest.setShortDescription("short");
        invalidRequest.setLongDescription("long");
        // title is missing (null), which @NotBlank will catch

        mockMvc.perform(post("/tasks/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.error", is("Validation Error")))
                .andExpect(jsonPath("$.errors.title", is("Title is required")));
    }

    @Test
    void createTask_whenServiceThrowsTaskException_shouldReturnBadRequest() throws Exception {
        doThrow(new TaskException("Service error during creation"))
            .when(taskService).createTask(anyString(), anyString(), anyString());

        mockMvc.perform(post("/tasks/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validTaskRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", is("Service error during creation")));
    }
    
    // --- Test deleteTask --- 

    @Test
    void deleteTask_whenIdExists_shouldReturnOk() throws Exception {
        Long taskId = 1L;
        doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/tasks/delete/{id}", taskId)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.message", is("Task deleted successfully")));

        verify(taskService, times(1)).deleteTask(taskId);
    }

    @Test
    void deleteTask_whenIdNotFound_shouldReturnBadRequest() throws Exception {
        Long taskId = 99L;
        doThrow(new TaskException("Task not found with id: " + taskId))
            .when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/tasks/delete/{id}", taskId)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", is("Task not found with id: " + taskId)));
    }
    
    // --- Test getAllTasks --- 

    @Test
    void getAllTasks_whenTasksExist_shouldReturnOkWithTaskList() throws Exception {
        List<Task> tasks = Arrays.asList(task1, new Task()); // Simplified second task
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is(task1.getTitle())));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getAllTasks_whenNoTasks_shouldReturnBadRequest() throws Exception {
        when(taskService.getAllTasks()).thenThrow(new TaskException("No tasks found"));

        mockMvc.perform(get("/tasks/list"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", is("No tasks found")));
    }

     @Test
    void createTask_whenTitleNotMatchingExpected_shouldReturnBadRequest() throws Exception {
        // This test now reflects that the DTO validation (@NotBlank) is primary.
        // If the JSON field name is wrong (e.g. "titl"), 'title' in TaskRequest will be null.
        String jsonRequestWithMisspelledTitleField = 
            "{\"titl\": \"A title\", \"shortDescription\": \"short\", \"longDescription\": \"long\"}";

        MvcResult result = mockMvc.perform(post("/tasks/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestWithMisspelledTitleField))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.error", is("Validation Error")))
                .andExpect(jsonPath("$.errors.title", is("Title is required")))
                .andReturn();
                
        // Verify that taskService.createTask was NOT called because validation failed earlier
        verify(taskService, never()).createTask(any(), any(), any());
    }
} 