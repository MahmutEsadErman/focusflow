package org.focusflow.repository;

import org.focusflow.model.Role;
import org.focusflow.model.Team;
import org.focusflow.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for the UserRepository using an in-memory H2 database.
 * 
 * This test suite covers the following scenarios:
 * - Creating a new user
 * - Finding a user by ID and email
 * - Updating and deleting users
 * - Querying users by role and team membership
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private Role roleUser;
    private Team teamAlpha;

    /**
     * Initializes test data before each test method.
     * Creates and persists a Role and a Team for relational test cases.
     */
    @BeforeEach
    void setUp() {
        roleUser = new Role();
        roleUser.setName("USER");
        entityManager.persist(roleUser);

        teamAlpha = new Team();
        teamAlpha.setName("Alpha");
        entityManager.persist(teamAlpha);
    }

    /**
     * Tests saving and retrieving a user by ID.
     */
    @Test
    @DisplayName("Create and find user by ID")
    void createAndFindById() {
        User user = createUser("user1@example.com");
        User saved = userRepository.save(user);

        Optional<User> found = userRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("user1@example.com");
    }

    /**
     * Tests finding a user by email.
     */
    @Test
    @DisplayName("Find user by email")
    void findByEmail() {
        User user = createUser("email@example.com");
        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("email@example.com");
        assertThat(found).isPresent();
    }

    /**
     * Tests updating user information (first name).
     */
    @Test
    @DisplayName("Update user information")
    void updateUser() {
        User user = createUser("update@example.com");
        user = userRepository.save(user);

        user.setFirstName("Updated");
        userRepository.save(user);

        Optional<User> updated = userRepository.findById(user.getId());
        assertThat(updated).isPresent();
        assertThat(updated.get().getFirstName()).isEqualTo("Updated");
    }

    /**
     * Tests deleting a user by ID.
     */
    @Test
    @DisplayName("Delete user")
    void deleteUser() {
        User user = createUser("delete@example.com");
        User saved = userRepository.save(user);

        userRepository.deleteById(saved.getId());

        Optional<User> deleted = userRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }

    /**
     * Tests querying users by role name using a many-to-many relationship.
     */
    @Test
    @DisplayName("Query users by role")
    void queryByRole() {
        User user = createUser("role@example.com");
        user.setRoles(new HashSet<>(List.of(roleUser)));
        userRepository.save(user);

        List<User> result = userRepository.findByRoles_Name("USER");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("role@example.com");
    }

    /**
     * Tests querying users by team membership using a many-to-one relationship.
     */
    @Test
    @DisplayName("Query users by team")
    void queryByTeam() {
        User user = createUser("team@example.com");
        user.setTeam(teamAlpha);
        userRepository.save(user);

        List<User> result = userRepository.findByTeam_Id(teamAlpha.getId());
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("team@example.com");
    }

    /**
     * Utility method to quickly create a basic User object.
     * 
     * @param email Email to assign to the user
     * @return a User object
     */
    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPassword("password123");
        return user;
    }
}
