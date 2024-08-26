package com.example.demo.architecture.domain.service;


import com.example.demo.architecture.commons.exception.DuplicateUserException;
import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.domain.model.gateway.UserResponseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserResponseRepository userRepository;

    @Override
    public List<UserResponse> findByEmail(String email) throws BadRequestException {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new BadRequestException("Usuario no encontrado") ;
        }
        return userRepository.findByEmail(email);
    }
    @Override
    public UserResponse createUser(UserRequest userRequest)  {
        if (userRepository.findByEmail(userRequest.getEmail()).isEmpty())
            return userRepository.saveUser(userRequest);
        else throw new DuplicateUserException("Correo duplicado");
    }

    @Override
    public Boolean disableUserById(UUID userId) {
        return userRepository.disableUserById(userId);
    }
}