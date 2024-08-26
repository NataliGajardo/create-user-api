package com.example.demo.architecture.infrastructure.h2repository;

import com.example.demo.architecture.domain.model.UserRequest;
import com.example.demo.architecture.infrastructure.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.UUID;

@RepositoryDefinition(domainClass = UserData.class, idClass = UUID.class)
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

List<UserData> findByEmail(String email);

}
