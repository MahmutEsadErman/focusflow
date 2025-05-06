package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.focusflow.model.Task;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskArchivingSteps {
    private Set<Task> activeTasks = new HashSet<>();
    private Set<Task> archivedTasks = new HashSet<>();
    private Task taskToArchive;

    @Given("the user is on the task dashboard")
    public void givenTheUserIsOnTheTaskDashboard() {
        // Set up dashboard simulation (no real UI here)
    }

    @Given("the task {string} is marked as completed")
    public void givenTheTaskIsMarkedAsCompleted(String taskName) {
        taskToArchive = new Task(taskName);
        activeTasks.add(taskToArchive); // Add task to active tasks
    }

    @When("the user clicks the {string} button next to the task")
    public void whenTheUserClicksTheButton(String button) {
        if ("Archive".equals(button)) {
            taskToArchive.archive(); // Perform archive action
            activeTasks.remove(taskToArchive); // Remove from active tasks
            archivedTasks.add(taskToArchive); // Add to archived tasks
        }
    }

    @Then("the task {string} should be moved to the archive")
    public void thenTheTaskShouldBeMovedToTheArchive(String taskName) {
        assertTrue(archivedTasks.stream()
                .anyMatch(task -> task.getTitle().equals(taskName) && task.isArchived));
    }

    @Then("it should no longer appear in the active task list")
    public void thenItShouldNoLongerAppearInTheActiveTaskList() {
        assertFalse(activeTasks.stream()
                .anyMatch(task -> task.getTitle().equals(taskToArchive.getTitle())));
    }
}
