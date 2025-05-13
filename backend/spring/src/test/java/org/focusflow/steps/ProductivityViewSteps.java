package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductivityViewSteps {
    private List<Map<String, String>> taskList;
    private List<Map<String, String>> filteredTasks;
    private String filterDate = "2025-05-05";

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        // Simulate task dashboard access
        taskList = new ArrayList<>();
    }

    @And("there are tasks with due dates including {string}")
    public void there_are_tasks_with_due_dates_including(String dueDate) {
        // Simulate tasks in the system
        taskList.add(Map.of("name", "Task A", "dueDate", dueDate));
        taskList.add(Map.of("name", "Task B", "dueDate", "2025-05-06"));
        taskList.add(Map.of("name", "Task C", "dueDate", dueDate));
    }

    @When("the user selects the {string} filter")
    public void the_user_selects_the_filter(String filter) {
        // Simulate filtering logic
        if ("Today".equals(filter)) {
            filteredTasks = taskList.stream()
                    .filter(task -> task.get("dueDate").equals(filterDate))
                    .collect(Collectors.toList());
        }
    }

    @Then("only tasks with a due date of {string} should be displayed")
    public void only_tasks_with_due_date_should_be_displayed(String dueDate) {
        assertFalse(filteredTasks.isEmpty(), "Filtered task list should not be empty");
        assertTrue(filteredTasks.stream().allMatch(task -> task.get("dueDate").equals(dueDate)));
    }

    @And("tasks with other due dates should not appear")
    public void tasks_with_other_due_dates_should_not_appear() {
        assertTrue(filteredTasks.stream().noneMatch(task -> !task.get("dueDate").equals(filterDate)));
    }
}
