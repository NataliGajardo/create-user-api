package com.example.demo.architecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

  @Id
  @Column(unique = true)
  private UUID id;
  private String username;
  private String password;
  private String email;
  private Boolean isActive;
  private String token;
  private LocalDateTime creationDate;
  private LocalDateTime modificationDate;
  private LocalDateTime lastLogin;
    @OneToMany(cascade = CascadeType.ALL)
  private List<PhoneData> phones;
}
