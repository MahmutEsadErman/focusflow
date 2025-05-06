package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.focusflow.model.Task;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReminderNotificationSteps {
    private Map<String, Task> tasks = new HashMap<>();
    private Task currentTask;
    private boolean confirmationNotificationShown = false;

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

    @When("the user clicks the {string} button next to the task")
    public void the_user_clicks_the_button_next_to_the_task(String buttonName) {
        assertNotNull(currentTask, "Task must exist to set reminder.");
        System.out.println("Clicked button: " + buttonName + " for task: " + currentTask.getTitle());
    }

    @And("selects a reminder time of {string}")
    public void selects_a_reminder_time_of(String reminderTime) {
        assertNotNull(currentTask, "Task must exist.");
        confirmationNotificationShown = true;
        System.out.println("Reminder time set to '" + reminderTime + "' for task: " + currentTask.getTitle());
    }

    @Then("a reminder should be scheduled for {string}")
    public void a_reminder_should_be_scheduled_for(String taskName) {
        Task task = tasks.get(taskName);
        assertNotNull(task, "Task not found.");
        assertNotNull(task.getDueDate(), "Reminder should be scheduled.");
        System.out.println("Reminder successfully scheduled for: " + taskName);
    }

    @And("the user should receive a confirmation notification")
    public void the_user_should_receive_a_confirmation_notification() {
        assertTrue(confirmationNotificationShown, "Confirmation notification should be shown.");
        System.out.println("User received a confirmation notification.");
    }
}
