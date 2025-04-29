package org.focusflow.service;

import org.focusflow.model.Role;
import org.focusflow.model.Team;
import org.focusflow.model.User;
import org.focusflow.repository.UserRepository;
import org.focusflow.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_WithValidCredentials_ShouldSucceed() {
        // Arrange
        String email = "test@example.com";
        String password = "ValidPass12!";
        String firstName = "John";
        String lastName = "Doe";

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L); // Simulate ID generation
            return user;
        });

        // Act
        User result = userService.registerUser(email, password, firstName, lastName);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertTrue(result.getRoles().stream().anyMatch(role -> role.getName().equals("USER")));

        verify(passwordEncoder).encode(password);
        verify(userRepository).existsByEmail(email);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_WithInvalidEmail_ShouldThrowException() {
        // Arrange
        String invalidEmail = "invalid-email";
        String password = "ValidPass12!";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(invalidEmail, password, "John", "Doe")
        );

        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithExistingEmail_ShouldThrowException() {
        // Arrange
        String email = "existing@example.com";
        String password = "ValidPass12!";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, password, "John", "Doe")
        );

        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithNullPassword_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String nullPassword = null;

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, nullPassword, "John", "Doe")
        );
        
        assertEquals("Password cannot be null", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithPasswordTooShort_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String shortPassword = "Short1!";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, shortPassword, "John", "Doe")
        );
        
        assertEquals("Password must be between 10 and 12 characters long", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithPasswordTooLong_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String longPassword = "ThisIsTooLong123!";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, longPassword, "John", "Doe")
        );
        
        assertEquals("Password must be between 10 and 12 characters long", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithPasswordMissingUppercase_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String noUppercase = "password12!";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, noUppercase, "John", "Doe")
        );
        
        assertEquals("Password must contain at least one uppercase letter", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithPasswordMissingLowercase_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String noLowercase = "PASSWORD12!";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, noLowercase, "John", "Doe")
        );
        
        assertEquals("Password must contain at least one lowercase letter", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_WithPasswordMissingSpecialChar_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String noSpecialChar = "Password123";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(email, noSpecialChar, "John", "Doe")
        );
        
        assertEquals("Password must contain at least one special character (!@#$%^&*)", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_WithCorrectCredentials_ShouldSucceed() {
        // Arrange
        String email = "test@example.com";
        String password = "ValidPass12!";
        
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail(email);
        existingUser.setPassword("encodedPassword");
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");
        
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(true);
        
        // Act
        User result = userService.login(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(passwordEncoder).matches(password, "encodedPassword");
        verify(userRepository).findByEmail(email);
    }

    @Test
    void login_WithIncorrectCredentials_ShouldThrowException() {
        // Arrange
        String email = "test@example.com";
        String password = "wrongpassword";
        
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail(email);
        existingUser.setPassword("encodedPassword");
        
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            userService.login(email, password)
        );

        verify(passwordEncoder).matches(password, "encodedPassword");
        verify(userRepository).findByEmail(email);
    }

    @Test
    void login_WithNonExistentUser_ShouldThrowException() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "ValidPass12!";
        
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            userService.login(email, password)
        );

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void assignRole_ShouldUpdateUserRole() {
        // Arrange
        Long userId = 1L;
        String roleName = "ADMIN";
        
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("test@example.com");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = userService.assignRole(userId, roleName);

        // Assert
        assertTrue(result.getRoles().stream().anyMatch(role -> role.getName().equals(roleName)));
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void assignRole_WithNonExistentUser_ShouldThrowException() {
        // Arrange
        Long userId = 999L;
        String roleName = "ADMIN";
        
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            userService.assignRole(userId, roleName)
        );

        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void joinTeam_ShouldUpdateUserTeam() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        
        Team team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.joinTeam(user, team);

        // Assert
        assertTrue(result.isPartOfTeam());
        assertEquals(team, result.getTeam());
        verify(userRepository).save(user);
    }

    @Test
    void leaveTeam_ShouldRemoveUserFromTeam() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        
        Team team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        
        user.setTeam(team);
        
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.leaveTeam(user);

        // Assert
        assertFalse(result.isPartOfTeam());
        assertNull(result.getTeam());
        verify(userRepository).save(user);
    }
}
