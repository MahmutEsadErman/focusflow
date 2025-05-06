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

public class TaskDependenciesSteps {
    private final Map<String, Task> taskMap = new HashMap<>();
    private Task selectedTask;
    private Task dependencyTask;

    @Given("the user is on the task dashboard")
    public void the_user_is_on_the_task_dashboard() {
        // Simulate dashboard access
        System.out.println("User is on the task dashboard");
    }

    @And("there are tasks {string} and {string}")
    public void there_are_tasks_and(String taskOne, String taskTwo) {
        taskMap.put(taskOne, new Task(taskOne));
        taskMap.put(taskTwo, new Task(taskTwo));
    }

    @When("the user clicks the {string} button next to {string}")
    public void the_user_clicks_the_button_next_to(String buttonLabel, String taskName) {
        selectedTask = taskMap.get(taskName);
        assertNotNull(selectedTask, "Task to edit should exist");
    }

    @And("selects {string} as the dependent task")
    public void selects_as_the_dependent_task(String dependencyName) {
        dependencyTask = taskMap.get(dependencyName);
        assertNotNull(dependencyTask, "Dependency task should exist");
        selectedTask.setDependency(dependencyTask);
    }

    @Then("{string} should be marked as dependent on {string}")
    public void should_be_marked_as_dependent_on(String taskName, String dependencyName) {
        Task task = taskMap.get(taskName);
        Task dependency = taskMap.get(dependencyName);
        assertTrue(task.hasDependencyOn(dependency), taskName + " should depend on " + dependencyName);
    }

    @And("the dependency should be visually indicated on the dashboard")
    public void the_dependency_should_be_visually_indicated_on_the_dashboard() {
        // Simulate a visual cue (e.g., arrow, icon)
        System.out.println("Dependency is visually shown on the dashboard");
    }
}
