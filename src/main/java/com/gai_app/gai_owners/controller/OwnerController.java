package com.gai_app.gai_owners.controller;

import com.gai_app.gai_owners.dto.OwnerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OwnerController {
    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(Long id);
    OwnerDto createOwner(OwnerDto ownerDto);
    OwnerDto updateOwner(Long id, OwnerDto ownerDto);
    void deleteOwner(Long id);
}
