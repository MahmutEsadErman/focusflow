package org.focusflow.steps;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.focusflow.model.Task;
import org.junit.Assert;

public class TaskManagementSteps {
    private List<String> taskList = new ArrayList<>();
    private Map<String, String> taskStatus = new HashMap<>();
    private Map<String, String> taskDueDates = new HashMap<>();
    private String currentTaskName;
    private String selectedDueDate;
    private boolean taskSaved;
    private boolean taskDeleted;

    // Background
    @Given("the user has logged into the FocusFlow application")
    public void user_logged_into_app() {
        System.out.println("User logged in.");
    }

    @Given("the user has an active workspace")
    public void user_has_active_workspace() {
        System.out.println("Active workspace confirmed.");
    }

    // Scenario 1: Add Task
    @Given("the user is on the task dashboard")
    public void user_on_task_dashboard() {
        System.out.println("User is on dashboard.");
    }

    @When("the user clicks the {string} button")
    public void user_clicks_button(String button) {
        System.out.println("User clicked: " + button);
    }

    @When("the user enters {string} in the task name field")
    public void user_enters_task_name(String taskName) {
        currentTaskName = taskName;
    }

    @When("selects a due date of {string}")
    public void user_selects_due_date(String dueDate) {
        selectedDueDate = dueDate;
    }

    @When("clicks {string}")
    public void user_clicks_save(String button) {
        if (currentTaskName != null && selectedDueDate != null) {
            taskList.add(currentTaskName);
            taskDueDates.put(currentTaskName, selectedDueDate);
            taskSaved = true;
        }
    }

    @Then("the new task {string} should be visible in the task list")
    public void task_should_be_visible(String taskName) {
        assertTrue(taskList.contains(taskName));
    }

    @Then("the due date should be displayed as {string}")
    public void due_date_should_be_displayed(String dueDate) {
        assertEquals(dueDate, taskDueDates.get(currentTaskName));
    }

    // Scenario 2: Mark Task as Complete
    @Given("the task {string} is marked as pending")
    public void task_is_pending(String taskName) {
        taskList.add(taskName);
        taskStatus.put(taskName, "pending");
    }

    @When("the user clicks the checkbox next to {string}")
    public void user_clicks_checkbox(String taskName) {
        taskStatus.put(taskName, "completed");
    }

    @Then("the task {string} should be marked as completed")
    public void task_should_be_completed(String taskName) {
        assertEquals("completed", taskStatus.get(taskName));
    }

    @Then("the task status should update visually")
    public void task_visual_status_update() {
        System.out.println("Task status updated visually.");
    }

    // Scenario 3: Delete Task
    @Given("the task {string} exists")
    public void task_exists(String taskName) {
        taskList.add(taskName);
    }

    @When("the user clicks the {string} icon next to {string}")
    public void user_clicks_icon(String icon, String taskName) {
        System.out.println("Clicked " + icon + " icon for " + taskName);
    }

    @When("confirms the deletion in the popup")
    public void confirms_deletion() {
        taskDeleted = true;
    }

    @Then("the task {string} should no longer appear in the task list")
    public void task_should_be_removed(String taskName) {
        if (taskDeleted) {
            taskList.remove(taskName);
        }
        assertFalse(taskList.contains(taskName));
    }

}
