package org.focusflow.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a task in the system.
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Column(nullable = false)
    private String title;

    private String shortDescription;
    private String longDescription;
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * Assigns a user to the task.
     * @param user the user to assign
     */
    public void assignUser(User user) {
        this.assignedUser = user;
    }

    /**
     * Unassigns the user from the task.
     */
    public void unassignUser() {
        this.assignedUser = null;
    }

    /**
     * Changes the priority of the task.
     * @param newPriority the new priority
     */
    public void changePriority(TaskPriority newPriority) {
        this.priority = newPriority;
    }

    /**
     * Changes the status of the task.
     * @param newStatus the new status
     */
    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Updates the details of the task.
     * @param title the new title
     * @param shortDescription the new short description
     * @param longDescription the new long description
     * @param dueDate the new due date
     */
    public void updateDetails(String title, String shortDescription, String longDescription, LocalDateTime dueDate) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.dueDate = dueDate;
    }

    /**
     * Checks if the task is overdue.
     * @return true if the task is overdue, false otherwise
     */
    public boolean isOverdue() {
        return this.dueDate != null && this.dueDate.isBefore(LocalDateTime.now());
    }
}
