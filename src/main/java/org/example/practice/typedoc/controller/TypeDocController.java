package org.example.practice.typedoc.controller;

import org.example.practice.typedoc.dto.TypeDocDto;
import org.example.practice.typedoc.service.TypeDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class TypeDocController {
    private final TypeDocService service;

    @Autowired
    public TypeDocController(TypeDocService service) {
        this.service = service;
    }

    @GetMapping()
    public List<TypeDocDto> typeDocs() {
        return service.docTypes();
    }

}
