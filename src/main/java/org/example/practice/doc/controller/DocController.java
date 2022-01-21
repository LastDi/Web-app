package org.example.practice.doc.controller;

import org.example.practice.doc.dto.DocDto;
import org.example.practice.doc.service.DocService;
import org.example.practice.office.dto.OfficeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

///Controller for testing
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class DocController {
    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @PostMapping(value = "/document")
    public void doc(@RequestBody DocDto docDto) {
        docService.add(docDto);
    }

    @GetMapping(value = "/document/list")
    public List<DocDto> docs() {
        return docService.docs();
    }
}
