package org.example.practice.doc.service;

import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.dto.DocDto;
import org.example.practice.doc.entity.Doc;
import org.example.practice.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocDao dao;
    private final Mapper mapper;

    public DocServiceImpl(DocDao dao, Mapper mapper) {
        this.dao = dao;
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
