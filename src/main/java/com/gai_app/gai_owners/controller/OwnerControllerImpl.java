package com.gai_app.gai_owners.controller;

import com.gai_app.gai_owners.dto.OwnerDto;
import com.gai_app.gai_owners.mapper.MappingUtils;
import com.gai_app.gai_owners.model.OwnerModel;
import com.gai_app.gai_owners.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners().stream()
                .map(mappingUtils::mapToOwnerDto)
                .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        OwnerDto ownerDto = mappingUtils.mapToOwnerDto(ownerService.getOwnerById(id));
        return new ResponseEntity<>(ownerDto, HttpStatus.OK);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto createOwner(@Valid @RequestBody OwnerDto ownerDto) {
        OwnerModel ownerModel = mappingUtils.mapToOwnerModelFromDto(ownerDto);
        return mappingUtils.mapToOwnerDto(ownerService.createOwner(ownerModel));
    }



    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerDto ownerDto) {
        OwnerModel ownerModel = mappingUtils.mapToOwnerModelFromDto(ownerDto);
        return mappingUtils.mapToOwnerDto(ownerService.updateOwner(id, ownerModel));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}