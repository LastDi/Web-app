package org.example.practice.doc.service;

import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.dto.DocDto;
import org.example.practice.doc.entity.Doc;
import org.example.practice.mapper.EntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocDao dao;
    private final EntityToDtoMapper mapper;

    @Autowired
    public DocServiceImpl(DocDao dao, EntityToDtoMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(DocDto docDto) {
        dao.save(mapper.map(docDto, Doc.class));
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
