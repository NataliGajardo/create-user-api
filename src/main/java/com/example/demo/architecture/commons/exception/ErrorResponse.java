package com.example.demo.architecture.commons.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String message;
    private String details;
    private HttpStatusCode status;
    private Integer code;
    private List<ValidationError> validationErrors;


    public ErrorResponse(String message, String details, HttpStatusCode status, Integer code) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.code = code;
        this.validationErrors = new ArrayList<>();
    }

    public void addValidationError(String field, String errorMessage) {
            this.validationErrors.add(new ValidationError(field, errorMessage));
        }




    }

