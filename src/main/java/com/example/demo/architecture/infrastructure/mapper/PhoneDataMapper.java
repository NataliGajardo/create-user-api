package com.example.demo.architecture.infrastructure.mapper;

import com.example.demo.architecture.domain.model.PhoneDTO;
import com.example.demo.architecture.infrastructure.entity.PhoneData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PhoneDataMapper {
   PhoneDataMapper INSTANCE = Mappers.getMapper( PhoneDataMapper.class );

    PhoneDTO sourceToDestination(PhoneData phoneData);
  List<PhoneData> toPhoneDataList(List<PhoneDTO> phoneDto);
    @Mapping(target = "id", expression = "java(getUuid())")
    PhoneData toPhoneData(PhoneDTO phoneDto);

    default UUID getUuid(){
      return UUID.randomUUID();
    }
}
