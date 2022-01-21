package org.example.practice.office.service;

import org.example.practice.mapper.EntityToDtoMapper;
import org.example.practice.office.dao.OfficeDao;
import org.example.practice.office.dto.*;
import org.example.practice.office.entity.Office;
import org.example.practice.organization.dao.OrganizationDao;
import org.example.practice.organization.dto.OrganizationDto;
import org.example.practice.organization.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService{

    private final OfficeDao dao;
    private final OrganizationDao orgDao;
    private final EntityToDtoMapper mapper;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao orgDao, EntityToDtoMapper mapper) {
        this.dao = dao;
        this.orgDao = orgDao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeDtoForSave dto) {
        Organization organization = orgDao.loadById(dto.getOrgId());
        Office office = new Office(dto.getName(), dto.getAddress(), dto.getPhone(), dto.isActive(), organization);
        dao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeDto> offices() {
        List<Office> offices = dao.all();
        return mapper.mapAll(offices, OfficeDto.class);
    }

    @Override
    @Transactional
    public void update(OfficeDtoForUpd dto) {
        Office office = dao.loadById(dto.getId());
        office.setName(dto.getName());
        office.setAddress(dto.getAddress());
        office.setPhone(dto.getPhone());
        office.setActive(dto.isActive());
        dao.update(office);
    }

    @Override
    @Transactional(readOnly = true)
    public OfficeDto office(Long id) {
        Office office = dao.loadById(id);
        if (office == null)
            throw new IllegalArgumentException("Office not found");
        return mapper.map(office, OfficeDto.class);
    }

    @Override
    @Transactional
    public List<OfficeDtoForListOut> officesByTerms(OfficeDtoForListIn dto) {
        return mapper.mapAll(dao.allByTerms(dto), OfficeDtoForListOut.class);
    }
}
