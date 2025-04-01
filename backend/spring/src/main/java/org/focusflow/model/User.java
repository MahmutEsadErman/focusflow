package org.focusflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * Joins the user to a team.
     * @param team the team to join
     */
    public void joinTeam(Team team) {
        if (this.team != null) {
            this.team.removeMember(this);
        }
        this.team = team;
        team.addMember(this);
    }

    /**
     * Leaves the current team.
     */
    public void leaveTeam() {
        if (this.team != null) {
            this.team.removeMember(this);
            this.team = null;
        }
    }

    /**
     * Checks if the user is part of a team.
     * @return true if the user is part of a team, false otherwise
     */
    public boolean isPartOfTeam() {
        return this.team != null;
    }

    /**
     * Assigns a task to the user.
     * @param task the task to assign
     */
    public void assignTask(Task task) {
        task.setAssignedUser(this);
    }

    /**
     * Unassigns a task from the user.
     * @param task the task to unassign
     */
    public void unassignTask(Task task) {
        if (task.getAssignedUser() == this) {
            task.setAssignedUser(null);
        }
    }

    /**
     * Checks if the user is assigned to a task.
     * @param task the task to check
     * @return true if the user is assigned to the task, false otherwise
     */
    public boolean isAssignedToTask(Task task) {
        return task.getAssignedUser() == this;
    }

    /**
     * Gets all tasks assigned to the user.
     * @param allTasks the set of all tasks
     * @return a set of tasks assigned to the user
     */
    public Set<Task> getAssignedTasks(Set<Task> allTasks) {
        Set<Task> userTasks = new HashSet<>();
        for (Task task : allTasks) {
            if (task.getAssignedUser() == this) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }
}
