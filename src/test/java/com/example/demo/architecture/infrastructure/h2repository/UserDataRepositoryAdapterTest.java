// UserDataRepositoryAdapterTest.java
package com.example.demo.architecture.infrastructure.h2repository;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.infrastructure.builder.UserDataBuilder;
import com.example.demo.architecture.infrastructure.entity.UserData;
import com.example.demo.architecture.infrastructure.mapper.UserDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDataRepositoryAdapterTest {

    @Mock
    private UserDataRepository jpaUserRepository;

    @Mock
    private UserDataMapper userDataMapper;

    @Mock
    private UserDataBuilder userDataBuilder;

    @InjectMocks
    private UserDataRepositoryAdapter userDataRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";
        UserData userData = new UserData();
        UserResponse userResponse = new UserResponse();
        when(jpaUserRepository.findByEmail(email)).thenReturn(Collections.singletonList(userData));
        when(userDataMapper.sourceToDestination(userData)).thenReturn(userResponse);

        List<UserResponse> result = userDataRepositoryAdapter.findByEmail(email);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jpaUserRepository, times(1)).findByEmail(email);
        verify(userDataMapper, times(1)).sourceToDestination(userData);
    }
    @Test
    void testFindByEmail_ReturnsEmptyList() {
        String email = "test@example.com";
        when(jpaUserRepository.findByEmail(email)).thenReturn(Collections.emptyList());

        List<UserResponse> result = userDataRepositoryAdapter.findByEmail(email);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jpaUserRepository, times(1)).findByEmail(email);
    }
    @Test
    void testCreateUser() {
        UserRequest userRequest = new UserRequest();
        UserData userData = new UserData();
        UserResponse userResponse = new UserResponse();
        when(userDataBuilder.buildFromRequest(userRequest)).thenReturn(userData);
        when(jpaUserRepository.save(userData)).thenReturn(userData);
        when(userDataMapper.sourceToDestination(userData)).thenReturn(userResponse);

        UserResponse result = userDataRepositoryAdapter.createUser(userRequest);

        assertNotNull(result);
        verify(userDataBuilder, times(1)).buildFromRequest(userRequest);
        verify(jpaUserRepository, times(1)).save(userData);
        verify(userDataMapper, times(1)).sourceToDestination(userData);
    }

    @Test
    void testDisableUserById() {
        UUID userId = UUID.randomUUID();
        UserData userData = new UserData();
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.of(userData));

        Boolean result = userDataRepositoryAdapter.disableUserById(userId);

        assertTrue(result);
        assertFalse(userData.getIsActive());
        verify(jpaUserRepository, times(1)).findById(userId);
        verify(jpaUserRepository, times(1)).save(userData);
    }

    @Test
    void testDisableUserById_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.empty());

        Boolean result = userDataRepositoryAdapter.disableUserById(userId);

        assertFalse(result);
        verify(jpaUserRepository, times(1)).findById(userId);
        verify(jpaUserRepository, never()).save(any(UserData.class));
    }
}