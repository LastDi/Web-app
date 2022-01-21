package org.example.practice.office.controller;

import org.example.practice.office.dto.*;
import org.example.practice.office.entity.Office;
import org.example.practice.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
    private final OfficeService officeService;
    private Map<String, String> result;
    private Map<String, String> error;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
        this.result = new HashMap<>(1);
        this.error = new HashMap<>(1);
        this.result.put("result", "success");
        this.error.put("error", "");
    }

    @PostMapping(value = "/save")
    public void saveOffice(@RequestBody OfficeDtoForSave dto) {
        officeService.add(dto);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<?>  getOffices(@RequestBody OfficeDtoForListIn dto) {
        return ResponseEntity.ok().body(officeService.officesByTerms(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updOffice(@RequestBody OfficeDtoForUpd dto) {
        officeService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneOffice(@PathVariable Long id) {
        OfficeDto dto;
        try {
            dto = officeService.office(id);
        } catch (IllegalArgumentException e) {
            error.replace("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().body(dto);
    }
}
