package org.example.practice.organization.controller;

import org.example.practice.organization.dto.*;
import org.example.practice.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrganization(@Validated @PathVariable Long id) {
        OrganizationDto organizationDto = organizationService.organization(id);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrganization(@RequestBody OrganizationDtoForSave dto) {
        organizationService.add(dto);
        return ResponseEntity.ok().body(Map.of("result", "success"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updOrganization(@RequestBody OrganizationDtoForUpd dto) {
        organizationService.update(dto);
        return ResponseEntity.ok().body(Map.of("result", "succsess"));
    }

    @PostMapping("/list")
    public ResponseEntity<?> getOrganizations(@Validated @RequestBody OrganizationDtoForListIn dto) {
        List<OrganizationDtoForListOut> organizations = organizationService.organizationsByTerms(dto);
        return ResponseEntity.ok().body(organizations);
    }
}
