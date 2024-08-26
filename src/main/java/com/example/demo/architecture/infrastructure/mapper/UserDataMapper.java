package com.example.demo.architecture.infrastructure.mapper;

import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.infrastructure.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserDataMapper {
   UserDataMapper INSTANCE = Mappers.getMapper( UserDataMapper.class );
    UserResponse sourceToDestination(UserData source);
    UserData destinationToSource(UserResponse response);

}
