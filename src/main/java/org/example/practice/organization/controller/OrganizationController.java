package org.example.practice.organization.controller;

import org.example.practice.organization.dto.*;
import org.example.practice.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Map<String, String> result;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
        this.result = new HashMap<>(1);
    }

    @PutMapping("/update")
    public Map<String, String> updOrganization(@RequestBody OrganizationDtoForUpd dto) {
        result.put("result", "success");
        try {
            organizationService.update(dto);
        } catch (IllegalArgumentException e) {
            result.replace("result", "failed");
        }
        return result;
    }

    @PostMapping("/save")
    public void saveOrganization(@RequestBody OrganizationDtoForSave dto) {
        organizationService.add(dto);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getOrganizations(@RequestBody OrganizationDtoForListIn dto) {
        List<OrganizationDtoForListOut> organizations = organizationService.organizationsByTerms(dto);
        System.out.println(dto);
        return ResponseEntity.ok().body(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrganization(@PathVariable Long id) {
        OrganizationDto organizationDto;
        try {
            organizationDto = organizationService.organization(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organization not found");
        }
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

}
