package org.focusflow.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class.
 */
public class UserTest {

    private User user;
    private Team team;
    private Task task;
    private Role userRole;
    private Role adminRole;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        
        team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        
        userRole = new Role("USER");
        adminRole = new Role("ADMIN");
        
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.getRoles().add(userRole); // Add default USER role
    }

    /**
     * Tests the creation of a User with valid data.
     */
    @Test
    public void testUserCreationWithValidData() {
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    /**
     * Tests joining a team.
     */
    @Test
    public void testJoinTeam() {
        user.joinTeam(team);
        assertEquals(team, user.getTeam());
        assertTrue(team.getMembers().contains(user));
    }

    /**
     * Tests leaving a team.
     */
    @Test
    public void testLeaveTeam() {
        user.joinTeam(team);
        user.leaveTeam();
        assertNull(user.getTeam());
        assertFalse(team.getMembers().contains(user));
    }

    /**
     * Tests if the user is part of a team.
     */
    @Test
    public void testIsPartOfTeam() {
        assertFalse(user.isPartOfTeam());
        user.joinTeam(team);
        assertTrue(user.isPartOfTeam());
    }

    /**
     * Tests assigning a task to the user.
     */
    @Test
    public void testAssignTask() {
        user.assignTask(task);
        assertEquals(user, task.getAssignedUser());
    }

    /**
     * Tests unassigning a task from the user.
     */
    @Test
    public void testUnassignTask() {
        user.assignTask(task);
        user.unassignTask(task);
        assertNull(task.getAssignedUser());
    }

    /**
     * Tests if the user is assigned to a task.
     */
    @Test
    public void testIsAssignedToTask() {
        assertFalse(user.isAssignedToTask(task));
        user.assignTask(task);
        assertTrue(user.isAssignedToTask(task));
    }

    /**
     * Tests getting all tasks assigned to the user.
     */
    @Test
    public void testGetAssignedTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        
        Set<Task> allTasks = new HashSet<>();
        allTasks.add(task1);
        allTasks.add(task2);

        user.assignTask(task1);
        
        Set<Task> userTasks = user.getAssignedTasks(allTasks);
        assertEquals(1, userTasks.size());
        assertTrue(userTasks.contains(task1));
        assertFalse(userTasks.contains(task2));
    }

    /**
     * Tests adding a role to the user.
     */
    @Test
    public void testAddRole() {
        assertTrue(user.getRoles().contains(userRole));
        assertFalse(user.getRoles().contains(adminRole));
        
        user.getRoles().add(adminRole);
        
        assertTrue(user.getRoles().contains(userRole));
        assertTrue(user.getRoles().contains(adminRole));
        assertEquals(2, user.getRoles().size());
    }

    /**
     * Tests removing a role from the user.
     */
    @Test
    public void testRemoveRole() {
        user.getRoles().add(adminRole);
        assertTrue(user.getRoles().contains(adminRole));
        
        user.getRoles().remove(adminRole);
        
        assertFalse(user.getRoles().contains(adminRole));
        assertTrue(user.getRoles().contains(userRole));
        assertEquals(1, user.getRoles().size());
    }

    /**
     * Tests if the user has a specific role.
     */
    @Test
    public void testHasRole() {
        assertTrue(user.getRoles().stream().anyMatch(role -> role.getName().equals("USER")));
        assertFalse(user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")));
        
        user.getRoles().add(adminRole);
        
        assertTrue(user.getRoles().stream().anyMatch(role -> role.getName().equals("USER")));
        assertTrue(user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")));
    }

    /**
     * Tests that removing a non-existent role does not affect existing roles.
     */
    @Test
    public void testRemoveNonExistentRole() {
        Role nonExistentRole = new Role("NON_EXISTENT");
        user.getRoles().remove(nonExistentRole);
        
        assertTrue(user.getRoles().contains(userRole));
        assertEquals(1, user.getRoles().size());
    }

    /**
     * Tests user creation with null values in optional fields.
     */
    @Test
    public void testUserCreationWithNullValues() {
        User newUser = new User();
        newUser.setId(1L);
        newUser.setEmail("test@example.com");
        // firstName and lastName are null
        
        assertNotNull(newUser);
        assertEquals("test@example.com", newUser.getEmail());
        assertNull(newUser.getFirstName());
        assertNull(newUser.getLastName());
    }

    /**
     * Tests user creation with empty strings.
     */
    @Test
    public void testUserCreationWithEmptyStrings() {
        User newUser = new User();
        newUser.setId(1L);
        newUser.setEmail("test@example.com");
        newUser.setFirstName("");
        newUser.setLastName("");
        
        assertNotNull(newUser);
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("", newUser.getFirstName());
        assertEquals("", newUser.getLastName());
    }

    /**
     * Tests user creation with maximum length values.
     */
    @Test
    public void testUserCreationWithMaxLengthValues() {
        String longString = "a".repeat(50); // Max length for name fields
        
        User newUser = new User();
        newUser.setId(1L);
        newUser.setEmail("test@example.com");
        newUser.setFirstName(longString);
        newUser.setLastName(longString);
        
        assertNotNull(newUser);
        assertEquals(longString, newUser.getFirstName());
        assertEquals(longString, newUser.getLastName());
    }

    /**
     * Tests multiple role assignments.
     */
    @Test
    public void testMultipleRoleAssignments() {
        Role developerRole = new Role("DEVELOPER");
        Role testerRole = new Role("TESTER");
        
        user.getRoles().add(developerRole);
        user.getRoles().add(testerRole);
        
        assertTrue(user.getRoles().contains(userRole));
        assertTrue(user.getRoles().contains(developerRole));
        assertTrue(user.getRoles().contains(testerRole));
        assertEquals(3, user.getRoles().size());
    }

    /**
     * Tests task priority handling.
     */
    @Test
    public void testTaskPriorityHandling() {
        Task highPriorityTask = new Task();
        highPriorityTask.setId(2L);
        highPriorityTask.setTitle("High Priority Task");
        highPriorityTask.setPriority(TaskPriority.HIGH);
        
        Task lowPriorityTask = new Task();
        lowPriorityTask.setId(3L);
        lowPriorityTask.setTitle("Low Priority Task");
        lowPriorityTask.setPriority(TaskPriority.LOW);
        
        user.assignTask(highPriorityTask);
        user.assignTask(lowPriorityTask);
        
        Set<Task> userTasks = user.getAssignedTasks(Set.of(highPriorityTask, lowPriorityTask));
        assertEquals(2, userTasks.size());
        assertTrue(userTasks.contains(highPriorityTask));
        assertTrue(userTasks.contains(lowPriorityTask));
    }

    /**
     * Tests task deadline management.
     */
    @Test
    public void testTaskDeadlineManagement() {
        Task taskWithDeadline = new Task();
        taskWithDeadline.setId(4L);
        taskWithDeadline.setTitle("Task with Deadline");
        taskWithDeadline.setDueDate(java.time.LocalDateTime.now().plusDays(7));
        
        user.assignTask(taskWithDeadline);
        
        assertTrue(user.isAssignedToTask(taskWithDeadline));
        assertNotNull(taskWithDeadline.getDueDate());
    }

}
