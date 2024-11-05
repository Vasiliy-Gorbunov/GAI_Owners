package com.gai_app.gai_owners.service;

import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.exception.ResourceNotFoundException;
import com.gai_app.gai_owners.mapper.MappingUtils;
import com.gai_app.gai_owners.model.OwnerModel;
import com.gai_app.gai_owners.repository.OwnerRepository;
import com.gai_app.gai_owners.service.kafka.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final MappingUtils mappingUtils;
    private final NotificationService notificationService;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, MappingUtils mappingUtils, NotificationService notificationService) {
        this.ownerRepository = ownerRepository;
        this.mappingUtils = mappingUtils;
        this.notificationService = notificationService;
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
                        .orElseThrow(() -> ThrowableMessage(id)));
    }


    @Override
    @Transactional
    public OwnerModel createOwner(OwnerModel ownerModel) {
        Owner owner = mappingUtils.mapToOwner(ownerModel);
        OwnerModel savedOwner = mappingUtils.mapToOwnerModelFromEntity(ownerRepository.save(owner));
        notificationService.getModelCreateMessageAndSend(savedOwner, "created");
        return savedOwner;
    }


    @Override
    @Transactional
    public OwnerModel updateOwner(Long id, OwnerModel updatedOwner) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage(id));

        mappingUtils.mapToOwner(updatedOwner);
        existingOwner.setName(updatedOwner.getName());
        existingOwner.setDob(updatedOwner.getDob());
        existingOwner.setGender(updatedOwner.getGender());
        updatedOwner = mappingUtils.mapToOwnerModelFromEntity(ownerRepository.save(existingOwner));
        notificationService.getModelCreateMessageAndSend(updatedOwner, "updated");
        return updatedOwner;
    }


    @Override
    @Transactional
    public void deleteOwner(Long id) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage(id));
        ownerRepository.deleteById(existingOwner.getId());
        notificationService.getModelCreateMessageAndSend(mappingUtils
                .mapToOwnerModelFromEntity(existingOwner), "deleted");
    }

    private ResourceNotFoundException ThrowableMessage(Long id) {
        return new ResourceNotFoundException("Owner with id " + id + " not found");
    }
}

