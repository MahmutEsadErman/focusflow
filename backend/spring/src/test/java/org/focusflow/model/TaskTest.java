package org.focusflow.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Task class.
 */
public class TaskTest {

    private Task task;
    private User user;
    private Team team;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        task = new Task();
        user = new User();
        team = new Team();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        task = null;
        user = null;
    }

    /**
     * Tests the creation of a Task with valid data.
     */
    @Test
    public void testTaskCreationWithValidData() {
        Task task = new Task(1L, "Title", "Short Desc", "Long Desc", LocalDateTime.now().plusDays(1),
                TaskPriority.MEDIUM, TaskStatus.PENDING, user, team);
        assertNotNull(task);
        assertEquals("Title", task.getTitle());
        assertEquals("Short Desc", task.getShortDescription());
        assertEquals("Long Desc", task.getLongDescription());
    }

    /**
     * Tests assigning a user to a Task.
     */
    @Test
    public void testAssignUser() {
        task.assignUser(user);
        assertEquals(user, task.getAssignedUser());
    }

    /**
     * Tests unassigning a user from a Task.
     */
    @Test
    public void testUnassignUser() {
        task.assignUser(user);
        task.unassignUser();
        assertNull(task.getAssignedUser());
    }

    /**
     * Tests changing the priority of a Task.
     */
    @Test
    public void testChangePriority() {
        task.changePriority(TaskPriority.HIGH);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    /**
     * Tests changing the status of a Task.
     */
    @Test
    public void testChangeStatus() {
        task.changeStatus(TaskStatus.PENDING);
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    /**
     * Tests updating the details of a Task.
     */
    @Test
    public void testUpdateDetails() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
        task.updateDetails("New Title", "Short Desc", "Long Desc", dueDate);
        assertEquals("New Title", task.getTitle());
        assertEquals("Short Desc", task.getShortDescription());
        assertEquals("Long Desc", task.getLongDescription());
        assertEquals(dueDate, task.getDueDate());
    }

    /**
     * Tests if a Task is overdue.
     */
    @Test
    public void testIsOverdue() {
        task.setDueDate(LocalDateTime.now().minusDays(1));
        assertTrue(task.isOverdue());

        task.setDueDate(LocalDateTime.now().plusDays(1));
        assertFalse(task.isOverdue());
    }

    /**
     * Tests the status transitions of a Task.
     */
    @Test
    public void testStatusTransitions() {
        task.changeStatus(TaskStatus.PENDING);
        assertEquals(TaskStatus.PENDING, task.getStatus());
        task.changeStatus(TaskStatus.CLOSED);
        assertEquals(TaskStatus.CLOSED, task.getStatus());
    }

    /**
     * Tests the validation of a Task's due date.
     */
    @Test
    public void testDueDateValidation() {
        task.setDueDate(LocalDateTime.now().plusDays(1));
        assertFalse(task.isOverdue());
        task.setDueDate(LocalDateTime.now().minusDays(1));
        assertTrue(task.isOverdue());
    }

    /**
     * Tests the management of a Task's priority.
     */
    @Test
    public void testPriorityManagement() {
        task.changePriority(TaskPriority.LOW);
        assertEquals(TaskPriority.LOW, task.getPriority());
        task.changePriority(TaskPriority.HIGH);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    /**
     * Tests task creation with null values in optional fields.
     */
    @Test
    public void testTaskCreationWithNullValues() {
        Task newTask = new Task(1L, "Title", "Short Desc", "Long Desc", LocalDateTime.now().plusDays(1),
                TaskPriority.MEDIUM, TaskStatus.PENDING, user, team);
        assertNotNull(newTask);
        assertEquals("Title", newTask.getTitle());
        assertNull(newTask.getShortDescription());
        assertNull(newTask.getLongDescription());
        assertNull(newTask.getDueDate());
        assertNull(newTask.getPriority());
        assertNull(newTask.getStatus());
    }

    /**
     * Tests task creation with empty strings.
     */
    @Test
    public void testTaskCreationWithEmptyStrings() {
        Task newTask = new Task(1L, "", "", "", LocalDateTime.now().plusDays(1), TaskPriority.MEDIUM,
                TaskStatus.PENDING, null, null);
        assertNotNull(newTask);
        assertEquals("", newTask.getTitle());
        assertEquals("", newTask.getShortDescription());
        assertEquals("", newTask.getLongDescription());
    }

    /**
     * Tests task creation with maximum length values.
     */
    @Test
    public void testTaskCreationWithMaxLengthValues() {
        String longString = "a".repeat(200); // Assuming max length is 200
        Task newTask = new Task(1L, longString, longString, longString, LocalDateTime.now().plusDays(1),
                TaskPriority.MEDIUM, TaskStatus.PENDING, null, null);
        assertNotNull(newTask);
        assertEquals(longString, newTask.getTitle());
        assertEquals(longString, newTask.getShortDescription());
        assertEquals(longString, newTask.getLongDescription());
    }

    /**
     * Tests the name field with different object types.
     */
    @Test
    public void testNameField() {
        task.setTitle("Task Name");
        assertEquals("Task Name", task.getTitle());

        task.setTitle(null);
        assertNull(task.getTitle());
    }
}
