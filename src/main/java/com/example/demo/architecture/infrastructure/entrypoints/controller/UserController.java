package com.example.demo.architecture.infrastructure.entrypoints.controller;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.service.UserServiceImpl;
import com.example.demo.architecture.infrastructure.entrypoints.controller.api.UserApi;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController  implements UserApi {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    public ResponseEntity<Object> registrarUsuario(@Valid @RequestBody UserRequest userRequest) throws Exception {
        var newUser = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    public ResponseEntity<Object> getUserByEmail(@RequestParam String email) throws BadRequestException {
        var users = userService.findByEmail(email);
            return ResponseEntity.ok().body(users);
        }

    @Override
    public ResponseEntity<Object> disableUserById(UUID userId) {
        return  Boolean.TRUE.equals(userService.disableUserById(userId)) ?
                ResponseEntity.ok().body("Usuario deshabilitado") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
}
