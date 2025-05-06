package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.focusflow.model.Task;
import org.focusflow.model.TaskPriority;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskPrioritizationSteps {
    private Map<String, Task> tasks = new HashMap<>();
    private Task currentTask;

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        System.out.println("User is on the task dashboard.");
    }

    @And("the task {string} exists")
    public void the_task_exists(String taskName) {
        Task task = new Task(taskName);
        tasks.put(taskName, task);
        currentTask = task;
        System.out.println("Task exists: " + taskName);
    }

    @When("the user clicks the {string} dropdown next to the task")
    public void the_user_clicks_the_dropdown_next_to_the_task(String dropdownName) {
        assertNotNull(currentTask, "Task must be selected.");
        System.out.println("Clicked dropdown: " + dropdownName + " for task: " + currentTask.getTitle());
    }

    @And("selects {string}")
    public void selects(TaskPriority priorityLevel) {
        assertNotNull(currentTask, "Task must be selected to assign priority.");
        currentTask.setPriority(priorityLevel);
        System.out.println("Selected priority: " + priorityLevel + " for task: " + currentTask.getTitle());
    }

    @Then("the priority of {string} should be updated to {string}")
    public void the_priority_of_should_be_updated_to(String taskName, String expectedPriority) {
        Task task = tasks.get(taskName);
        assertNotNull(task, "Task not found.");
        assertEquals(expectedPriority, task.getPriority(), "Priority mismatch.");
        System.out.println("Verified priority of task '" + taskName + "' is: " + expectedPriority);
    }

    @And("the task should be visually marked with a priority indicator")
    public void the_task_should_be_visually_marked_with_a_priority_indicator() {
        assertNotNull(currentTask, "Task must be selected.");
        assertNotEquals("None", currentTask.getPriority(), "Task must have a priority to show an indicator.");
        System.out.println(
                "Task '" + currentTask.getTitle() + "' has priority indicator for: " + currentTask.getPriority());
    }
}
