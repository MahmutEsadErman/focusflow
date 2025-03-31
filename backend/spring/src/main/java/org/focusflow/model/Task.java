package org.focusflow.model;
import lombok.Data;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


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

    // Helper Methods
    public void assignUser(User user) {
        this.assignedUser = user;
    }

    public void unassignUser() {
        this.assignedUser = null;
    }

    public void changePriority(TaskPriority newPriority) {
        this.priority = newPriority;
    }

    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    public void updateDetails(String title, String shortDescription, String longDescription, LocalDateTime dueDate) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return this.dueDate != null && this.dueDate.isBefore(LocalDateTime.now());
    }
}

