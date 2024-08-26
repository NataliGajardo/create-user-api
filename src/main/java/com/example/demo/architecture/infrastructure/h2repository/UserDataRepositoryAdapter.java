package com.example.demo.architecture.infrastructure.h2repository;


import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.infrastructure.builder.UserDataBuilder;
import com.example.demo.architecture.infrastructure.entity.UserData;
import com.example.demo.architecture.infrastructure.mapper.UserDataMapper;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDataRepositoryAdapter {

  private final UserDataRepository jpaUserRepository;
  private final UserDataMapper userDataMapper;
  private final UserDataBuilder userDataBuilder;



  public UserDataRepositoryAdapter(UserDataRepository jpaUserRepository, UserDataMapper userDataMapper, UserDataBuilder userDataBuilder) {
      this.jpaUserRepository = jpaUserRepository;
      this.userDataMapper = userDataMapper;
      this.userDataBuilder = userDataBuilder;
  }

  public List<UserResponse> findByEmail(String email) {
    List<UserData> userDataList = jpaUserRepository.findByEmail(email);

    if (userDataList != null && !userDataList.isEmpty()) {
      return userDataList.stream()
              .map(userDataMapper::sourceToDestination)
              .toList();
    }
    return Collections.emptyList();
  }
  public UserResponse createUser(UserRequest userRequest) {
    var userData= userDataBuilder.buildFromRequest(userRequest);
    UserData savedUserData = jpaUserRepository.save(userData);
    return userDataMapper.sourceToDestination(savedUserData);
  }
  public Boolean disableUserById(UUID userId) {
    Optional<UserData> userData = jpaUserRepository.findById(userId);
    if (userData.isPresent()) {
      UserData userDelete =userData.get();
      userDelete.setIsActive(false);
      jpaUserRepository.save(userDelete);
        return true;
    }
    return false;
  }
}
