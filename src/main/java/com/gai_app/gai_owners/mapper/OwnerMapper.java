package com.gai_app.gai_owners.mapper;

import com.gai_app.gai_owners.dto.OwnerDto;
import com.gai_app.gai_owners.entity.Gender;
import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.model.OwnerModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDto toOwnerDto(OwnerModel model);

    OwnerModel toOwnerModelFromDto(OwnerDto dto);

    OwnerModel toOwnerModelFromEntity(Owner owner);

    Owner toOwner(OwnerModel model);

    // Маппинг списка владельцев
    List<OwnerDto> toOwnerDtoList(List<OwnerModel> ownerModels);

    List<OwnerModel> toOwnerModelListFromDto(List<OwnerDto> ownerDtos);

    List<OwnerModel> toOwnerModelListFromEntity(List<Owner> owners);

    List<Owner> toOwnerList(List<OwnerModel> ownerModels);

    // Маппинг для Enum поля Gender
    @Mapping(source = "gender", target = "gender")
    default String mapGenderToString(Gender gender) {
        return gender != null ? gender.name() : null;
    }

    @Mapping(source = "gender", target = "gender")
    default Gender mapStringToGender(String gender) {
        return gender != null ? Gender.valueOf(gender) : null;
    }
}
