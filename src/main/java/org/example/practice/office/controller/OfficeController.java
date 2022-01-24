package org.example.practice.office.controller;

import org.example.practice.office.dto.*;
import org.example.practice.office.entity.Office;
import org.example.practice.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneOffice(@PathVariable Long id) {
        OfficeDto dto = officeService.office(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOffice(@Validated @RequestBody OfficeDtoForSave dto) {
        officeService.add(dto);
        return ResponseEntity.ok().body(Map.of("result", "success"));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updOffice(@Validated @RequestBody OfficeDtoForUpd dto) {
        officeService.update(dto);
        return ResponseEntity.ok().body(Map.of("result", "success"));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<?>  getOffices(@Validated @RequestBody OfficeDtoForListIn dto) {
        return ResponseEntity.ok().body(officeService.officesByTerms(dto));
    }
}
