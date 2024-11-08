package com.gai_app.gai_owners.controller;

import com.gai_app.gai_owners.dto.OwnerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OwnerController {
    List<OwnerDto> getAllOwners();
    ResponseEntity<OwnerDto> getOwnerById(Long id);
    OwnerDto createOwner(OwnerDto ownerDto);
    OwnerDto updateOwner(Long id, OwnerDto ownerDto);
    void deleteOwner(Long id);
}
