package com.gai_app.gai_owners.service;

import com.gai_app.gai_owners.model.OwnerModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerService {
    List<OwnerModel> getAllOwners();

    OwnerModel getOwnerById(Long id);

    OwnerModel createOwner(OwnerModel owner);


    OwnerModel updateOwner(Long id, OwnerModel updatedOwner);

    void deleteOwner(Long id);
}
