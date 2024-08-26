package com.example.demo.architecture.domain.model.gateway;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.infrastructure.h2repository.UserDataRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserResponseDataImpl implements UserResponseRepository {
    private final UserDataRepositoryAdapter userDataRepositoryAdapter;

    public UserResponseDataImpl(UserDataRepositoryAdapter userDataRepositoryAdapter) {
        this.userDataRepositoryAdapter = userDataRepositoryAdapter;
    }

    @Override
    public List<UserResponse> findByEmail(String email) {
        return userDataRepositoryAdapter.findByEmail(email);
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        return userDataRepositoryAdapter.createUser(userRequest);
    }

    @Override
    public Boolean disableUserById(UUID userId) {
        return userDataRepositoryAdapter.disableUserById(userId);
    }
}
