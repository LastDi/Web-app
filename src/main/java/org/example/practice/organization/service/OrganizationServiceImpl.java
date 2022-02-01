package org.example.practice.organization.service;

import org.example.practice.handler.exception.EntityNotFoundException;
import org.example.practice.mapper.EntityToDtoMapperImpl;
import org.example.practice.mapper.Mapper;
import org.example.practice.organization.dao.OrganizationDao;
import org.example.practice.organization.dto.*;
import org.example.practice.organization.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;
    private final Mapper mapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationDtoForSave dto) {
        Organization organization = new Organization (
                dto.getName(),
                dto.getFullName(),
                dto.getInn(),
                dto.getKpp(),
                dto.getAddress(),
                dto.getPhone(),
                Boolean.parseBoolean(dto.isActive())
        );
        dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDto> organizations() {
        List<Organization> organizations = dao.all();
        return mapper.mapAll(organizations, OrganizationDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationDtoForUpd dto) {
        Organization organization = dao.loadById(dto.getId());
        organization.setName(dto.getName());
        organization.setFullName(dto.getFullName());
        organization.setInn(dto.getInn());
        organization.setKpp(dto.getKpp());
        organization.setAddress(dto.getAddress());
        if (dto.isActive() != null)
            organization.setActive(Boolean.parseBoolean(dto.isActive()));
        if (dto.getPhone() != null)
            organization.setPhone(dto.getPhone());
        dao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDto organization(Long id) {
        Organization organization = dao.loadById(id);
        return mapper.map(organization, OrganizationDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationDtoForListOut> organizationsByTerms(OrganizationDtoForListIn dto) {
        return mapper.mapAll(dao.allByTerms(dto), OrganizationDtoForListOut.class);
    }
}
