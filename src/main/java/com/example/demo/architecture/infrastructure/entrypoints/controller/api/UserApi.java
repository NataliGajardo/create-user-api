package com.example.demo.architecture.infrastructure.entrypoints.controller.api;

import com.example.demo.architecture.commons.exception.ErrorResponse;
import com.example.demo.architecture.domain.model.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api")
public interface UserApi {
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Error con los parámetros ingresados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/createuser")
     ResponseEntity<Object> registrarUsuario(@Valid @RequestBody UserRequest userRequest) throws Exception;
    @Operation(summary = "Get user by email", description = "Retrieves a user by their email address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/user")
     ResponseEntity<Object> getUserByEmail(@RequestParam String email) throws BadRequestException;
@PatchMapping("/disableuser")
    @Operation(summary = "Disable user by id", description = "Disables a user by their id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User disabled successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
     ResponseEntity<Object> disableUserById(@RequestParam UUID userId);
}
