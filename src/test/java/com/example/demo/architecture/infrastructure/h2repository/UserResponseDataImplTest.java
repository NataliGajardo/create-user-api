// UserResponseDataImplTest.java
package com.example.demo.architecture.infrastructure.h2repository;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.domain.model.gateway.UserResponseDataImpl;
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

class UserResponseDataImplTest {

    @Mock
    private UserDataRepositoryAdapter userDataRepositoryAdapter;

    @InjectMocks
    private UserResponseDataImpl userResponseDataImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";
        UserResponse userResponse = new UserResponse();
        when(userDataRepositoryAdapter.findByEmail(email)).thenReturn(Collections.singletonList(userResponse));

        List<UserResponse> result = userResponseDataImpl.findByEmail(email);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userDataRepositoryAdapter, times(1)).findByEmail(email);
    }
    @Test
    void testFindByEmail_ReturnsEmptyList() {
        String email = "test@example.com";
        when(userDataRepositoryAdapter.findByEmail(email)).thenReturn(Collections.emptyList());

        List<UserResponse> result = userResponseDataImpl.findByEmail(email);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userDataRepositoryAdapter, times(1)).findByEmail(email);
    }
    @Test
    void testSaveUser() {
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();
        when(userDataRepositoryAdapter.createUser(userRequest)).thenReturn(userResponse);

        UserResponse result = userResponseDataImpl.saveUser(userRequest);

        assertNotNull(result);
        verify(userDataRepositoryAdapter, times(1)).createUser(userRequest);
    }

    @Test
    void testDisableUserById() {
        UUID userId = UUID.randomUUID();
        when(userDataRepositoryAdapter.disableUserById(userId)).thenReturn(true);

        Boolean result = userResponseDataImpl.disableUserById(userId);

        assertTrue(result);
        verify(userDataRepositoryAdapter, times(1)).disableUserById(userId);
    }
}