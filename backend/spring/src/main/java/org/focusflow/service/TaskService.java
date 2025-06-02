package org.focusflow.service;

import org.focusflow.exception.TaskException;
import org.focusflow.model.Task;
import org.focusflow.model.TaskPriority;
import org.focusflow.model.TaskStatus;
import org.focusflow.model.User;
import org.focusflow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public void createTask(String title, String longDescription, String shortDescription, LocalDateTime dueDate) {
        // Validate title value
        if (title == null || title.trim().isEmpty()) {
            throw new TaskException("Task title cannot be empty");
        }

        // Validate longDescription value
        if (longDescription == null || longDescription.trim().isEmpty()) {
            throw new TaskException("Long description cannot be empty");
        }

        // Validate shortDescription value
        if (shortDescription == null || shortDescription.trim().isEmpty()) {
            throw new TaskException("Short description cannot be empty");
        }

        Task task = new Task();
        User user = userService.login("test@test.de", "Password123!");
        task.assignUser(user);
        task.setTitle(title);
        task.setDueDate(dueDate);
        task.setLongDescription(longDescription);
        task.setShortDescription(shortDescription);
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.OPEN);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
       return taskRepository.findAll();

    }
}
