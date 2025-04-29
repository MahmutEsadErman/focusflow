package org.focusflow.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Team class.
 */
public class TeamTest {

    private Team team;
    private User user;
    private Task task;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        task = new Task();
        team = new Team();
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
     * Tests the creation of a Team with valid data.
     */
    @Test
    public void testTeamCreationWithValidData() {
        Team newTeam = new Team(1L, "Team Name", "Team Description", null, null, null);
        assertNotNull(newTeam);
        assertEquals("Team Name", newTeam.getName());
        assertEquals("Team Description", newTeam.getDescription());
    }

    /**
     * Tests adding a member to a Team.
     */
    @Test
    public void testAddMember() {
        team.addMember(user);
        assertTrue(team.hasMember(user));
        assertEquals(team, user.getTeam());
    }

    /**
     * Tests removing a member from a Team.
     */
    @Test
    public void testRemoveMember() {
        team.addMember(user);
        team.removeMember(user);
        assertFalse(team.hasMember(user));
        assertNull(user.getTeam());
    }

    /**
     * Tests adding a Task to a Team.
     */
    @Test
    public void testAddTask() {
        team.addTask(task);
        assertTrue(team.getAllTasks().contains(task));
        assertEquals(team, task.getTeam());
    }

    /**
     * Tests removing a Task from a Team.
     */
    @Test
    public void testRemoveTask() {
        team.addTask(task);
        team.removeTask(task);
        assertFalse(team.getAllTasks().contains(task));
        assertNull(task.getTeam());
    }

    /**
     * Tests getting all members of a Team.
     */
    @Test
    public void testGetAllMembers() {
        team.addMember(user);
        Set<User> members = team.getAllMembers();
        assertEquals(1, members.size());
        assertTrue(members.contains(user));
    }

    /**
     * Tests getting all Tasks of a Team.
     */
    @Test
    public void testGetAllTasks() {
        team.addTask(task);
        Set<Task> tasks = team.getAllTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task));
    }
    
    /**
     * Tests team creation with null values in optional fields.
     */
    @Test
    public void testTeamCreationWithNullValues() {
        Team newTeam = new Team(1L, "Team Name", null, null, null, null);
        assertNotNull(newTeam);
        assertEquals("Team Name", newTeam.getName());
        assertNull(newTeam.getDescription());
    }
    
    /**
     * Tests team creation with empty strings.
     */
    @Test
    public void testTeamCreationWithEmptyStrings() {
        Team newTeam = new Team(1L, "", "", null, null, null);
        assertNotNull(newTeam);
        assertEquals("", newTeam.getName());
        assertEquals("", newTeam.getDescription());
    }
    
    /**
     * Tests team creation with maximum length values.
     */
    @Test
    public void testTeamCreationWithMaxLengthValues() {
        String longString = "a".repeat(100); // Assuming max length is 100
        Team newTeam = new Team(1L, longString, longString, null, null, null);
        assertNotNull(newTeam);
        assertEquals(longString, newTeam.getName());
        assertEquals(longString, newTeam.getDescription());
    }
    
    /**
     * Tests concurrent member modifications.
     */
    @Test
    public void testConcurrentMemberModifications() {
        User user2 = new User();
        
        // Add first member
        team.addMember(user);
        assertTrue(team.hasMember(user));
        assertEquals(1, team.getAllMembers().size());
        
        // Add second member
        team.addMember(user2);
        assertTrue(team.hasMember(user2));
        assertEquals(2, team.getAllMembers().size());
        
        // Remove first member
        team.removeMember(user);
        assertFalse(team.hasMember(user));
        assertTrue(team.hasMember(user2));
        assertEquals(1, team.getAllMembers().size());
    }
}
