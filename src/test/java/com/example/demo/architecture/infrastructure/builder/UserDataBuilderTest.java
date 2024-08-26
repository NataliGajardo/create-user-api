package com.example.demo.architecture.infrastructure.builder;

import com.example.demo.architecture.commons.configuration.JWTConfig;
import com.example.demo.architecture.domain.model.PhoneDTO;
import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.infrastructure.entity.PhoneData;
import com.example.demo.architecture.infrastructure.entity.UserData;
import com.example.demo.architecture.infrastructure.mapper.PhoneDataMapper;
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

class UserDataBuilderTest {

    @Mock
    private PhoneDataMapper phoneDataMapper;

    @Mock
    private JWTConfig jwtConfig;

    @InjectMocks
    private UserDataBuilder userDataBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildFromRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");
        userRequest.setName("Test User");
        userRequest.setPhones(Collections.singletonList(new PhoneDTO()));

        UUID userId = UUID.randomUUID();
        String token = "generatedToken";
        when(jwtConfig.createToken(userRequest.getEmail(), true)).thenReturn(token);
        when(phoneDataMapper.toPhoneDataList(userRequest.getPhones())).thenReturn(Collections.singletonList(new PhoneData()));

        UserData userData = userDataBuilder.buildFromRequest(userRequest);

        assertNotNull(userData);
        assertEquals(userRequest.getEmail(), userData.getEmail());
        assertEquals(userRequest.getPassword(), userData.getPassword());
        assertEquals(userRequest.getName(), userData.getUsername());
        assertTrue(userData.getIsActive());
        assertEquals(token, userData.getToken());
        assertNotNull(userData.getCreationDate());
        assertNotNull(userData.getModificationDate());
        assertNotNull(userData.getLastLogin());
        assertNotNull(userData.getPhones());
        verify(jwtConfig, times(1)).createToken(userRequest.getEmail(), true);
        verify(phoneDataMapper, times(1)).toPhoneDataList(userRequest.getPhones());
    }

    @Test
    void testGetPhoneDataList() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPhones(Collections.singletonList(new PhoneDTO()));
        UUID userId = UUID.randomUUID();
        PhoneData phoneData = new PhoneData();
        when(phoneDataMapper.toPhoneDataList(userRequest.getPhones())).thenReturn(Collections.singletonList(phoneData));

        List<PhoneData> phoneDataList = userDataBuilder.getPhoneDataList(userRequest, userId);

        assertNotNull(phoneDataList);
        assertEquals(1, phoneDataList.size());
        assertEquals(userId, phoneDataList.get(0).getUserId());
        verify(phoneDataMapper, times(1)).toPhoneDataList(userRequest.getPhones());
    }
}