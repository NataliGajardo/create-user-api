package com.example.demo.architecture.domain.service;

import com.example.demo.architecture.commons.exception.DuplicateUserException;
import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.domain.model.gateway.UserResponseRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserResponseRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() throws BadRequestException {
        String email = "test@example.com";
        UserResponse userResponse = new UserResponse();
        when(userRepository.findByEmail(email)).thenReturn(Collections.singletonList(userResponse));

        List<UserResponse> result = userService.findByEmail(email);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(2)).findByEmail(email);
    }

    @Test
    void testCreateUser() throws BadRequestException {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        UserResponse userResponse = new UserResponse();
        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Collections.emptyList());
        when(userRepository.saveUser(userRequest)).thenReturn(userResponse);

        UserResponse result = userService.createUser(userRequest);

        assertNotNull(result);
        verify(userRepository, times(1)).findByEmail(userRequest.getEmail());
        verify(userRepository, times(1)).saveUser(userRequest);
    }

    @Test
    void testCreateUser_UserAlreadyExists() throws BadRequestException {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test2@example.com");
        UserResponse userResponse = new UserResponse();
        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Collections.singletonList(userResponse));

        assertThrows(DuplicateUserException.class, () -> {
            userService.createUser(userRequest);
        });

        verify(userRepository, times(1)).findByEmail(userRequest.getEmail());
        verify(userRepository, never()).saveUser(userRequest);
    }

    @Test
    void testDisableUserById() {
        UUID userId = UUID.randomUUID();
        when(userRepository.disableUserById(userId)).thenReturn(true);

        Boolean result = userService.disableUserById(userId);

        assertTrue(result);
        verify(userRepository, times(1)).disableUserById(userId);
    }
}