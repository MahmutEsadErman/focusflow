package org.focusflow.controller;

import jakarta.validation.Valid;
import org.focusflow.controller.DTOs.ApiResponse;
import org.focusflow.controller.DTOs.TaskRequest;
import org.focusflow.exception.TaskException;
import org.focusflow.model.Task;
import org.focusflow.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        logger.debug("Received task request: {}", taskRequest);

        if (taskRequest == null) {
            logger.error("Task request is unexpectedly null despite @RequestBody.");
            throw new TaskException("Task request cannot be null.");
        }

        try {
            taskService.createTask(taskRequest.getTitle(), taskRequest.getLongDescription(),
                    taskRequest.getShortDescription(), taskRequest.getDueDate());
            ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Task created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (TaskException te) {
            logger.warn("Propagating TaskException from service layer: {}", te.getMessage());
            throw te;
        } catch (Exception e) {
            logger.error("Unexpected error processing task request, wrapping in TaskException", e);
            throw new TaskException("An unexpected error occurred while processing the task request: " + e.getMessage(),
                    e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long id) {
        if (id == null) {
            throw new TaskException("Task ID cannot be null");
        }
        taskService.deleteTask(id);
        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Task deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println(">>> Listing tasks...");
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
}
