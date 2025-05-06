package org.focusflow.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.focusflow.model.Task;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskCommentsSteps {
    private Task currentTask;
    private String newComment;
    List<String> comments;

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        System.out.println("User is on the task dashboard.");
    }

    @And("the task {string} exists")
    public void the_task_exists(String taskName) {
        currentTask = new Task(taskName);
        System.out.println("Task created: " + taskName);
    }

    @When("the user clicks the {string} button next to the task")
    public void the_user_clicks_the_button_next_to_the_task(String button) {
        System.out.println("User clicked: " + button);
        // Simulated button click (no logic needed here for now)
    }

    @And("enters {string} in the comment field")
    public void enters_in_the_comment_field(String comment) {
        newComment = comment;
        System.out.println("User entered comment: " + comment);
    }

    @And("clicks {string}")
    public void clicks(String button) {
        if (button.equalsIgnoreCase("Post Comment")) {
            comments.add(newComment);
            System.out.println("Comment posted.");
        }
    }

    @Then("the comment {string} should be added to the task")
    public void the_comment_should_be_added_to_the_task(String expectedComment) {
        assertTrue(comments.contains(expectedComment), "The comment should be added to the task.");
        System.out.println("Comment successfully added.");
    }

    @And("the comment should be visible under the task")
    public void the_comment_should_be_visible_under_the_task() {
        for (String comment : comments) {
            System.out.println("Visible comment: " + comment);
        }
        assertFalse(comments.isEmpty(), "At least one comment should be visible.");
    }
}
