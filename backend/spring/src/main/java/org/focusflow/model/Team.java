package org.focusflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a team in the system.
 */
@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

    /**
     * Adds a member to the team.
     * @param user the user to add
     */
    public void addMember(User user) {
        members.add(user);
        user.setTeam(this);
    }

    /**
     * Removes a member from the team.
     * @param user the user to remove
     */
    public void removeMember(User user) {
        members.remove(user);
        user.setTeam(null);
    }

    /**
     * Checks if a user is a member of the team.
     * @param user the user to check
     * @return true if the user is a member, false otherwise
     */
    public boolean hasMember(User user) {
        return members.contains(user);
    }

    /**
     * Adds a task to the team.
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setTeam(this);
    }

    /**
     * Removes a task from the team.
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setTeam(null);
    }

    /**
     * Gets all members of the team.
     * @return a set of all members
     */
    public Set<User> getAllMembers() {
        return new HashSet<>(members);
    }

    /**
     * Gets all tasks of the team.
     * @return a set of all tasks
     */
    public Set<Task> getAllTasks() {
        return new HashSet<>(tasks);
    }
}
