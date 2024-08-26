package com.example.demo.architecture.infrastructure.mapper;

import com.example.demo.architecture.domain.model.PhoneDTO;
import com.example.demo.architecture.infrastructure.entity.PhoneData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T00:29:57-0400",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class PhoneDataMapperImpl implements PhoneDataMapper {

    @Override
    public PhoneDTO sourceToDestination(PhoneData phoneData) {
        if ( phoneData == null ) {
            return null;
        }

        PhoneDTO.PhoneDTOBuilder phoneDTO = PhoneDTO.builder();

        phoneDTO.number( phoneData.getNumber() );
        phoneDTO.citycode( phoneData.getCitycode() );
        phoneDTO.contrycode( phoneData.getContrycode() );

        return phoneDTO.build();
    }

    @Override
    public List<PhoneData> toPhoneDataList(List<PhoneDTO> phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }

        List<PhoneData> list = new ArrayList<PhoneData>( phoneDto.size() );
        for ( PhoneDTO phoneDTO : phoneDto ) {
            list.add( toPhoneData( phoneDTO ) );
        }

        return list;
    }

    @Override
    public PhoneData toPhoneData(PhoneDTO phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }

        PhoneData.PhoneDataBuilder phoneData = PhoneData.builder();

        phoneData.number( phoneDto.getNumber() );
        phoneData.citycode( phoneDto.getCitycode() );
        phoneData.contrycode( phoneDto.getContrycode() );

        phoneData.id( getUuid() );

        return phoneData.build();
    }
}
