package org.example.practice.doc.service;

import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.dto.DocDto;
import org.example.practice.doc.entity.Doc;
import org.example.practice.mapper.EntityToDtoMapperImpl;
import org.example.practice.mapper.Mapper;
import org.example.practice.typedoc.dao.TypeDocDao;
import org.example.practice.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocDao dao;
    private final UserDao userDao;
    private final TypeDocDao typeDocDao;
    private final Mapper mapper;

    public DocServiceImpl(DocDao dao, UserDao userDao, TypeDocDao typeDocDao, Mapper mapper) {
        this.dao = dao;
        this.userDao = userDao;
        this.typeDocDao = typeDocDao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocDto> docs() {
        List<Doc> docs = dao.all();
        return mapper.mapAll(docs, DocDto.class);
    }
}
