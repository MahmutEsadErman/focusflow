package org.focusflow.service;

import org.focusflow.model.Role;
import org.focusflow.model.Team;
import org.focusflow.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Interface for user management operations.
 */
public interface UserService {
    /**
     * Registers a new user with the system.
     *
     * @param email     the user's email address
     * @param password  the user's password
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return the newly created user
     * @throws IllegalArgumentException if the email is invalid, already exists, or password validation fails
     */
    User registerUser(String email, String password, String firstName, String lastName);

    /**
     * Authenticates a user with their email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return the authenticated user
     * @throws IllegalArgumentException if the credentials are invalid
     */
    User login(String email, String password);

    /**
     * Assigns a role to a user.
     *
     * @param userId   the ID of the user
     * @param roleName the name of the role to assign
     * @return the updated user
     * @throws IllegalArgumentException if the user is not found
     */
    User assignRole(Long userId, String roleName);

    /**
     * Adds a user to a team.
     *
     * @param user the user to add to the team
     * @param team the team to add the user to
     * @return the updated user
     */
    User joinTeam(User user, Team team);

    /**
     * Removes a user from their current team.
     *
     * @param user the user to remove from the team
     * @return the updated user
     */
    User leaveTeam(User user);
}
