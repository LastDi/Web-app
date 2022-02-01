package org.example.practice.organization.controller;

import org.example.practice.organization.dto.*;
import org.example.practice.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OrganizationDto getOneOrganization(@PathVariable Long id) {
        return organizationService.organization(id);
    }

    @PostMapping("/save")
    public void saveOrganization(@Validated @RequestBody OrganizationDtoForSave dto) {
        organizationService.add(dto);
    }

    @PostMapping("/list")
    public List<OrganizationDtoForListOut> getOrganizations(@Validated @RequestBody OrganizationDtoForListIn dto) {
        return organizationService.organizationsByTerms(dto);
    }

    @PostMapping("/update")
    public void updOrganization(@Validated @RequestBody OrganizationDtoForUpd dto) {
        organizationService.update(dto);
    }
}
