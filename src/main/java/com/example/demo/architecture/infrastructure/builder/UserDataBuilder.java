package com.example.demo.architecture.infrastructure.builder;

import com.example.demo.architecture.commons.configuration.JWTConfig;
import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.infrastructure.entity.PhoneData;
import com.example.demo.architecture.infrastructure.entity.UserData;
import com.example.demo.architecture.infrastructure.mapper.PhoneDataMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class UserDataBuilder {
 private  final PhoneDataMapper phoneDataMapper;
 private final JWTConfig jwtConfig;

    public UserDataBuilder(PhoneDataMapper phoneDataMapper, JWTConfig jwtConfig) {
        this.phoneDataMapper = phoneDataMapper;
        this.jwtConfig = jwtConfig;
    }

    public UserData buildFromRequest(UserRequest request) {
        var userId = UUID.randomUUID();
  return UserData.builder()
    .email(request.getEmail())
    .password(request.getPassword())
          .id(userId)
          .username(request.getName())
          .isActive(true)
          .token(jwtConfig.createToken(request.getEmail(),true))
          .creationDate(LocalDateTime.now())
            .modificationDate(LocalDateTime.now())
            .lastLogin(LocalDateTime.now())
          .phones(getPhoneDataList(request,userId))
    .build();
 }

 public  List<PhoneData> getPhoneDataList(UserRequest request, UUID userId) {
        var phoneDataList = phoneDataMapper.toPhoneDataList(request.getPhones());
        phoneDataList.forEach(phoneData -> phoneData.setUserId(userId));
    return phoneDataList;
 }
}
