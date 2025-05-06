package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.focusflow.model.Task;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskFilteringSteps {
    private List<Task> allTasks = new ArrayList<>();
    private List<Task> filteredTasks = new ArrayList<>();

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        System.out.println("User navigated to the task dashboard.");
    }

    @And("there are tasks with various priority levels")
    public void there_are_tasks_with_various_priority_levels() {
        allTasks.clear();
        allTasks.add(new Task(null, "Task A", "High", "John Doe", null, null, null, null, null));
        allTasks.add(new Task(null, "Task B", "Medium", "Jane Smith", null, null, null, null, null));
        allTasks.add(new Task(null, "Task C", "Low", "Alice Johnson", null, null, null, null, null));
        System.out.println("Tasks with priorities High, Medium, Low have been added.");
    }

    @When("the user selects {string} from the filter dropdown")
    public void the_user_selects_from_the_filter_dropdown(String filter) {
        filteredTasks.clear();
        if (filter.equalsIgnoreCase("High Priority")) {
            for (Task task : allTasks) {
                if ("High".equalsIgnoreCase(task.getPriority().toString())) {
                    filteredTasks.add(task);
                }
            }
        }
        System.out.println("Filtered by: " + filter);
    }

    @Then("only tasks with a priority of {string} should be displayed")
    public void only_tasks_with_a_priority_of_should_be_displayed(String expectedPriority) {
        for (Task task : filteredTasks) {
            assertEquals(expectedPriority, task.getPriority(),
                    "Only tasks with priority " + expectedPriority + " should be displayed.");
        }
        System.out.println("Displayed tasks have priority: " + expectedPriority);
    }

    @And("tasks with other priorities should not appear")
    public void tasks_with_other_priorities_should_not_appear() {
        for (Task task : filteredTasks) {
            assertEquals("High", task.getPriority());
        }
        System.out.println("Confirmed no other priorities are shown.");
    }

    @And("there are tasks assigned to multiple team members")
    public void there_are_tasks_assigned_to_multiple_team_members() {
        allTasks.clear();
        allTasks.add(new Task(null, "Task X", "High", "John Doe", null, null, null, null, null));
        allTasks.add(new Task(null, "Task Y", "Medium", "Jane Smith", null, null, null, null, null));
        allTasks.add(new Task(null, "Task Z", "Low", "John Doe", null, null, null, null, null));
        System.out.println("Tasks assigned to John Doe and others added.");
    }

    @When("the user selects {string} from the {string} filter")
    public void the_user_selects_from_the_assignee_filter(String selectedAssignee, String filterType) {
        filteredTasks.clear();
        if (filterType.equalsIgnoreCase("Assignee")) {
            for (Task task : allTasks) {
                if (selectedAssignee.equalsIgnoreCase(task.getAssignedUser().getLastName())) {
                    filteredTasks.add(task);
                }
            }
        }
        System.out.println("Filtered by assignee: " + selectedAssignee);
    }

    @Then("only tasks assigned to {string} should be displayed")
    public void only_tasks_assigned_to_should_be_displayed(String assignee) {
        for (Task task : filteredTasks) {
            assertEquals(assignee, task.getAssignedUser(), "Only tasks assigned to " + assignee + " should be shown.");
        }
        System.out.println("Filtered tasks are all assigned to: " + assignee);
    }

    @And("tasks assigned to other team members should not appear")
    public void tasks_assigned_to_other_team_members_should_not_appear() {
        for (Task task : filteredTasks) {
            assertEquals("John Doe", task.getAssignedUser());
        }
        System.out.println("Confirmed that tasks assigned to other team members are not shown.");
    }
}
