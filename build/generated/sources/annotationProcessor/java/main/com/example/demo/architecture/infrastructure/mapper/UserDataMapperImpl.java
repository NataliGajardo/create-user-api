package com.example.demo.architecture.infrastructure.mapper;

import com.example.demo.architecture.domain.model.PhoneDTO;
import com.example.demo.architecture.domain.model.UserResponse;
import com.example.demo.architecture.infrastructure.entity.PhoneData;
import com.example.demo.architecture.infrastructure.entity.UserData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T00:23:03-0400",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserDataMapperImpl implements UserDataMapper {

    @Override
    public UserResponse sourceToDestination(UserData source) {
        if ( source == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( source.getId() );
        userResponse.username( source.getUsername() );
        userResponse.email( source.getEmail() );
        userResponse.token( source.getToken() );
        if ( source.getIsActive() != null ) {
            userResponse.isActive( source.getIsActive() );
        }
        userResponse.creationDate( source.getCreationDate() );
        userResponse.modificationDate( source.getModificationDate() );
        userResponse.lastLogin( source.getLastLogin() );
        userResponse.phones( phoneDataListToPhoneDTOList( source.getPhones() ) );

        return userResponse.build();
    }

    @Override
    public UserData destinationToSource(UserResponse response) {
        if ( response == null ) {
            return null;
        }

        UserData.UserDataBuilder userData = UserData.builder();

        userData.id( response.getId() );
        userData.username( response.getUsername() );
        userData.email( response.getEmail() );
        userData.token( response.getToken() );
        userData.creationDate( response.getCreationDate() );
        userData.modificationDate( response.getModificationDate() );
        userData.lastLogin( response.getLastLogin() );
        userData.phones( phoneDTOListToPhoneDataList( response.getPhones() ) );

        return userData.build();
    }

    protected PhoneDTO phoneDataToPhoneDTO(PhoneData phoneData) {
        if ( phoneData == null ) {
            return null;
        }

        PhoneDTO.PhoneDTOBuilder phoneDTO = PhoneDTO.builder();

        phoneDTO.number( phoneData.getNumber() );
        phoneDTO.citycode( phoneData.getCitycode() );
        phoneDTO.contrycode( phoneData.getContrycode() );

        return phoneDTO.build();
    }

    protected List<PhoneDTO> phoneDataListToPhoneDTOList(List<PhoneData> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDTO> list1 = new ArrayList<PhoneDTO>( list.size() );
        for ( PhoneData phoneData : list ) {
            list1.add( phoneDataToPhoneDTO( phoneData ) );
        }

        return list1;
    }

    protected PhoneData phoneDTOToPhoneData(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        PhoneData.PhoneDataBuilder phoneData = PhoneData.builder();

        phoneData.number( phoneDTO.getNumber() );
        phoneData.citycode( phoneDTO.getCitycode() );
        phoneData.contrycode( phoneDTO.getContrycode() );

        return phoneData.build();
    }

    protected List<PhoneData> phoneDTOListToPhoneDataList(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneData> list1 = new ArrayList<PhoneData>( list.size() );
        for ( PhoneDTO phoneDTO : list ) {
            list1.add( phoneDTOToPhoneData( phoneDTO ) );
        }

        return list1;
    }
}
