package com.gai_app.gai_owners.mapper;

import com.gai_app.gai_owners.dto.OwnerDto;
import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.model.OwnerModel;

public interface MappingUtils {
    public OwnerDto mapToOwnerDto(OwnerModel model);

    public OwnerModel mapToOwnerModelFromDto(OwnerDto dto);

    public OwnerModel mapToOwnerModelFromEntity(Owner owner);

    public Owner mapToOwner(OwnerModel model);
}
