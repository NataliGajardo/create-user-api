package com.example.demo.architecture.domain.service;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;

import java.util.List;
import java.util.UUID;


public interface UserService {

    List<UserResponse> findByEmail(String email) throws Exception;

    UserResponse createUser(UserRequest userRequest) throws Exception;
    Boolean disableUserById(UUID userId);
}
