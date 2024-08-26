// UserControllerTest.java
package com.example.demo.architecture.infrastructure.entrypoints.controller;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.domain.model.gateway.UserResponseRepository;
import com.example.demo.architecture.domain.service.UserServiceImpl;
import com.example.demo.architecture.infrastructure.h2repository.UserDataRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    UserResponseRepository userResponseRepository = mock(UserResponseRepository.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuario() throws Exception {
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();
        when(userService.createUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<Object> response = userController.registrarUsuario(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
        verify(userService, times(1)).createUser(userRequest);
    }

    @Test
    void testGetUserByEmail_UserFound() throws BadRequestException {
        String email = "test@example.com";
        UserResponse userResponse = new UserResponse();
        when(userService.findByEmail(email)).thenReturn(Collections.singletonList(userResponse));

        ResponseEntity<Object> response = null;
        try {
            response = userController.getUserByEmail(email);
        } catch (org.apache.coyote.BadRequestException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(userResponse), response.getBody());
        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testDisableUserById_UserFound() {
        UUID userId = UUID.randomUUID();
        when(userService.disableUserById(userId)).thenReturn(true);

        ResponseEntity<Object> response = userController.disableUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario deshabilitado", response.getBody());
        verify(userService, times(1)).disableUserById(userId);
    }

    @Test
    void testDisableUserById_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userService.disableUserById(userId)).thenReturn(false);

        ResponseEntity<Object> response = userController.disableUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado", response.getBody());
        verify(userService, times(1)).disableUserById(userId);
    }
}