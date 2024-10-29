package com.gai_app.gai_owners.service;

import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.exception.ResourceNotFoundException;
import com.gai_app.gai_owners.mapper.MappingUtils;
import com.gai_app.gai_owners.model.OwnerModel;
import com.gai_app.gai_owners.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, MappingUtils mappingUtils) {
        this.ownerRepository = ownerRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OwnerModel> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(mappingUtils::mapToOwnerModelFromEntity)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public OwnerModel getOwnerById(Long id) {
        return mappingUtils
                .mapToOwnerModelFromEntity(ownerRepository.findById(id)
                        .orElseThrow(() -> ThrowableMessage("Owner", id)));
    }


    @Override
    @Transactional
    public OwnerModel createOwner(OwnerModel ownerModel) {
        Owner owner = mappingUtils.mapToOwner(ownerModel);
        return mappingUtils.mapToOwnerModelFromEntity(ownerRepository.save(owner));
    }


    @Override
    @Transactional
    public OwnerModel updateOwner(Long id, OwnerModel updatedOwner) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage("Owner", id));

        mappingUtils.mapToOwner(updatedOwner);
        existingOwner.setName(updatedOwner.getName());
        existingOwner.setDob(updatedOwner.getDob());
        existingOwner.setGender(updatedOwner.getGender());

        return mappingUtils.mapToOwnerModelFromEntity(ownerRepository.save(existingOwner));
    }


    @Override
    @Transactional
    public void deleteOwner(Long id) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage("Car", id));
        ownerRepository.deleteById(existingOwner.getId());
    }

    private ResourceNotFoundException ThrowableMessage(String obj, Long id) {
        return new ResourceNotFoundException(obj + " with id " + id + " not found");
    }
}

