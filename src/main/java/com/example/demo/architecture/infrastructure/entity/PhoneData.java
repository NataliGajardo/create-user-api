package com.example.demo.architecture.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneData {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID userId;
    private String number;
    private String citycode;
    private String contrycode;
}
