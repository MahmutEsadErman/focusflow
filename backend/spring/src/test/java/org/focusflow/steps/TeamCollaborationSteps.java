package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TeamCollaborationSteps {
    private Map<String, String> taskAssignments = new HashMap<>();
    private String currentTask;
    private String notification;

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        // Simulated navigation to task dashboard
        System.out.println("User is on the task dashboard.");
    }

    @And("the user has created a task named {string}")
    public void the_user_has_created_a_task_named(String taskName) {
        currentTask = taskName;
        taskAssignments.put(taskName, null); // task created but not yet assigned
        System.out.println("Task created: " + taskName);
    }

    @When("the user clicks the {string} icon next to the task")
    public void the_user_clicks_the_icon_next_to_the_task(String iconName) {
        // Simulate clicking an icon - for BDD this step is descriptive
        assertNotNull(currentTask, "Task must be created before interacting with it.");
        System.out.println("Clicked on icon: " + iconName + " for task: " + currentTask);
    }

    @And("selects {string} from the team member list")
    public void selects_from_the_team_member_list(String assignee) {
        taskAssignments.put(currentTask, assignee);
        notification = assignee + " has been notified about the assigned task.";
        System.out.println("Assigned " + currentTask + " to " + assignee);
    }

    @Then("the task {string} should be assigned to {string}")
    public void the_task_should_be_assigned_to(String taskName, String assignee) {
        assertEquals(assignee, taskAssignments.get(taskName), "Task assignment mismatch.");
        System.out.println("Verified: " + taskName + " is assigned to " + assignee);
    }

    @And("{string} should receive a notification about the assigned task")
    public void should_receive_a_notification_about_the_assigned_task(String assignee) {
        assertTrue(notification.contains(assignee), "No notification found for: " + assignee);
        System.out.println("Notification sent to " + assignee + ": " + notification);
    }
}
