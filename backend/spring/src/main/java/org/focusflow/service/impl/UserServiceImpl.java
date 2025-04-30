package org.focusflow.service.impl;

import org.focusflow.model.Role;
import org.focusflow.model.Team;
import org.focusflow.model.User;
import org.focusflow.repository.UserRepository;
import org.focusflow.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Configuration
class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    // Separate patterns for each validation rule
    private static final Pattern HAS_UPPERCASE = Pattern.compile(".*[A-Z].*");
    private static final Pattern HAS_LOWERCASE = Pattern.compile(".*[a-z].*");
    private static final Pattern HAS_SPECIAL_CHAR = Pattern.compile(".*[!@#$%^&*].*");
    private static final Pattern HAS_VALID_LENGTH = Pattern.compile(".{10,12}");

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password, String firstName, String lastName) {
        // Validate email format
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Validate password with specific exceptions
        validatePassword(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
        // Add default user role
        Role userRole = new Role("USER");
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    }

    @Override
    public User assignRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        Role role = new Role(roleName);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    @Override
    public User joinTeam(User user, Team team) {
        user.joinTeam(team);
        return userRepository.save(user);
    }

    @Override
    public User leaveTeam(User user) {
        user.leaveTeam();
        return userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        
        if (!HAS_VALID_LENGTH.matcher(password).matches()) {
            throw new IllegalArgumentException("Password must be between 10 and 12 characters long");
        }
        
        if (!HAS_UPPERCASE.matcher(password).matches()) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }
        
        if (!HAS_LOWERCASE.matcher(password).matches()) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }
        
        if (!HAS_SPECIAL_CHAR.matcher(password).matches()) {
            throw new IllegalArgumentException("Password must contain at least one special character (!@#$%^&*)");
        }
    }
} 