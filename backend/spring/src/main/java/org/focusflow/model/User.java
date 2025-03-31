package org.focusflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Helper Methods
    public void joinTeam(Team team) {
        if (this.team != null) {
            this.team.removeMember(this);
        }
        this.team = team;
        team.addMember(this);
    }

    public void leaveTeam() {
        if (this.team != null) {
            this.team.removeMember(this);
            this.team = null;
        }
    }

    public boolean isPartOfTeam() {
        return this.team != null;
    }

    public void assignTask(Task task) {
        task.setAssignedUser(this);
    }

    public void unassignTask(Task task) {
        if (task.getAssignedUser() == this) {
            task.setAssignedUser(null);
        }
    }

    public boolean isAssignedToTask(Task task) {
        return task.getAssignedUser() == this;
    }

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