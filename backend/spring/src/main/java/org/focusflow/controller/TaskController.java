package org.focusflow.controller;

import org.focusflow.controller.DTOs.TaskRequest;
import org.focusflow.model.Task;
import org.focusflow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public String createTask(@RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest.getTitle(), taskRequest.getLongDescription(), taskRequest.getShortDescription());
        return "Task created";
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return true;
    }

    @GetMapping("/list")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
