package org.example.practice.office.controller;

import org.example.practice.office.dto.*;
import org.example.practice.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OfficeDto getOneOffice(@PathVariable Long id) {
        return officeService.office(id);
    }

    @PostMapping(value = "/save")
    public void saveOffice(@Validated @RequestBody OfficeDtoForSave dto) {
        officeService.add(dto);
    }

    @PostMapping(value = "/list")
    public List<OfficeDtoForListOut>  getOffices(@Validated @RequestBody OfficeDtoForListIn dto) {
        return officeService.officesByTerms(dto);
    }

    @PostMapping(value = "/update")
    public void updOffice(@Validated @RequestBody OfficeDtoForUpd dto) {
        officeService.update(dto);
    }
}
