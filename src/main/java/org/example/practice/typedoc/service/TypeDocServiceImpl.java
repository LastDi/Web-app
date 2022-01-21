package org.example.practice.typedoc.service;

import org.example.practice.mapper.EntityToDtoMapper;
import org.example.practice.typedoc.dao.TypeDocDao;
import org.example.practice.typedoc.entity.TypeDoc;
import org.example.practice.typedoc.dto.TypeDocDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeDocServiceImpl implements TypeDocService {
    private final TypeDocDao dao;
    private final EntityToDtoMapper mapper;

    @Autowired
    public TypeDocServiceImpl(TypeDocDao dao, EntityToDtoMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void add(TypeDocDto typeDocDto) {
        dao.save(mapper.map(typeDocDto, TypeDoc.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDocDto> docTypes() {
        List<TypeDoc> all = dao.all();
        return mapper.mapAll(all, TypeDocDto.class);
    }
}
