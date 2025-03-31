package org.focusflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    // Helper Methods
    public void addMember(User user) {
        members.add(user);
        user.setTeam(this);
    }

    public void removeMember(User user) {
        members.remove(user);
        user.setTeam(null);
    }

    public boolean hasMember(User user) {
        return members.contains(user);
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setTeam(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setTeam(null);
    }

    public Set<User> getAllMembers() {
        return new HashSet<>(members);
    }

    public Set<Task> getAllTasks() {
        return new HashSet<>(tasks);
    }
}

