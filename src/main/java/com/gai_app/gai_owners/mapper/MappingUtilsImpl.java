package com.gai_app.gai_owners.mapper;

import com.gai_app.gai_owners.dto.OwnerDto;
import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.model.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingUtilsImpl implements MappingUtils {

    @Autowired
    private OwnerMapper ownerMapper;

    public OwnerDto mapToOwnerDto(OwnerModel model) {
        return ownerMapper.toOwnerDto(model);
    }

    public OwnerModel mapToOwnerModelFromDto(OwnerDto dto) {
        return ownerMapper.toOwnerModelFromDto(dto);
    }

    public OwnerModel mapToOwnerModelFromEntity(Owner owner) {
        return ownerMapper.toOwnerModelFromEntity(owner);
    }

    public Owner mapToOwner(OwnerModel model) {
        return ownerMapper.toOwner(model);
    }
}
