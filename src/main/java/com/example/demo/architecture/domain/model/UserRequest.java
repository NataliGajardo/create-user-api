package com.example.demo.architecture.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import jakarta.validation.constraints.Pattern;
import java.util.List;

import static com.example.demo.architecture.commons.constants.Constants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(
            regexp = EMAIL_REGEX,
            message = EMAIL_REGEX_MESSAGE
    )
    private String email;
    @Pattern(
            regexp = PASSWORD_REGEX,
            message = PASSWORD_REGEX_MESSAGE
    )
    private String password;
    private List<PhoneDTO> phones;
}
