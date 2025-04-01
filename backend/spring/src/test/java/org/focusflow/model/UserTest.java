package org.focusflow.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the User class.
 */
public class UserTest {

    private User user;
    private Team team;
    private Task task;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        task = mock(Task.class);
        team = mock(Team.class);
        user = new User();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        task = null;
        team = null;
        user = null;
    }

    /**
     * Tests the creation of a User with valid data.
     */
    @Test
    public void testUserCreationWithValidData() {
        User newUser = new User(1L, "test@example.com", "password123", "John", "Doe", null, null, null);
        assertNotNull(newUser);
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
        assertEquals("John", newUser.getFirstName());
        assertEquals("Doe", newUser.getLastName());
    }

    /**
     * Tests joining a team.
     */
    @Test
    public void testJoinTeam() {
        user.joinTeam(team);
        assertEquals(team, user.getTeam());
        verify(team).addMember(user);
    }

    /**
     * Tests leaving a team.
     */
    @Test
    public void testLeaveTeam() {
        user.joinTeam(team);
        user.leaveTeam();
        assertNull(user.getTeam());
        verify(team).removeMember(user);
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
        verify(task).setAssignedUser(user);
    }

    /**
     * Tests unassigning a task from the user.
     */
    @Test
    public void testUnassignTask() {
        when(task.getAssignedUser()).thenReturn(user);
        user.unassignTask(task);
        verify(task).setAssignedUser(null);
    }

    /**
     * Tests if the user is assigned to a task.
     */
    @Test
    public void testIsAssignedToTask() {
        when(task.getAssignedUser()).thenReturn(user);
        assertTrue(user.isAssignedToTask(task));
        when(task.getAssignedUser()).thenReturn(null);
        assertFalse(user.isAssignedToTask(task));
    }

    /**
     * Tests getting all tasks assigned to the user.
     */
    @Test
    public void testGetAssignedTasks() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        Set<Task> allTasks = new HashSet<>();
        allTasks.add(task1);
        allTasks.add(task2);

        when(task1.getAssignedUser()).thenReturn(user);
        when(task2.getAssignedUser()).thenReturn(null);

        Set<Task> userTasks = user.getAssignedTasks(allTasks);
        assertEquals(1, userTasks.size());
        assertTrue(userTasks.contains(task1));
    }
}
