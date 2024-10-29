package com.gai_app.gai_owners.controller;

import com.gai_app.gai_owners.dto.OwnerDto;
import com.gai_app.gai_owners.mapper.MappingUtils;
import com.gai_app.gai_owners.model.OwnerModel;
import com.gai_app.gai_owners.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerControllerImpl implements OwnerController {


    private final OwnerService ownerService;

    private final MappingUtils mappingUtils;

    public OwnerControllerImpl(OwnerService ownerService, MappingUtils mappingUtils) {
        this.ownerService = ownerService;
        this.mappingUtils = mappingUtils;
    }


    @Override
    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners().stream()
                .map(mappingUtils::mapToOwnerDto)
                .collect(Collectors.toList());
    }


    @Override
    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable Long id) {
        return mappingUtils.mapToOwnerDto(ownerService.getOwnerById(id));
    }


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto createOwner(@Valid @RequestBody OwnerDto ownerDto) {
        OwnerModel ownerModel = mappingUtils.mapToOwnerModelFromDto(ownerDto);
        return mappingUtils.mapToOwnerDto(ownerService.createOwner(ownerModel));
    }


    @Override
    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerDto ownerDto) {
        OwnerModel ownerModel = mappingUtils.mapToOwnerModelFromDto(ownerDto);
        return mappingUtils.mapToOwnerDto(ownerService.updateOwner(id, ownerModel));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}