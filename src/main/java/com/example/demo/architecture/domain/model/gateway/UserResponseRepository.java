package com.example.demo.architecture.domain.model.gateway;


import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserResponseRepository {
 List<UserResponse> findByEmail(String email);
 UserResponse saveUser(UserRequest userRequest);
 Boolean disableUserById(UUID userId);


}
