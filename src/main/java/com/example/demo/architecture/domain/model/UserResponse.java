package com.example.demo.architecture.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private String token;
    private boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDateTime lastLogin;
    private List<PhoneDTO> phones;
}
