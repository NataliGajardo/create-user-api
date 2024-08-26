package com.example.demo.architecture.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
