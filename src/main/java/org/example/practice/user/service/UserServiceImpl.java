package org.example.practice.user.service;

import org.example.practice.country.dao.CountryDao;
import org.example.practice.country.entity.Country;
import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.entity.Doc;
import org.example.practice.handler.exception.EntityNotFoundException;
import org.example.practice.mapper.Mapper;
import org.example.practice.office.dao.OfficeDao;
import org.example.practice.office.entity.Office;
import org.example.practice.typedoc.dao.TypeDocDao;
import org.example.practice.typedoc.entity.TypeDoc;
import org.example.practice.user.dao.UserDao;
import org.example.practice.user.dto.*;
import org.example.practice.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    private final DocDao docDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final TypeDocDao typeDocDao;
    private final Mapper mapper;
    private User user;
    private Doc doc;
    private Country country;
    private Office office;
    private TypeDoc typeDoc;

    public UserServiceImpl(UserDao dao, Mapper mapper, OfficeDao officeDao, CountryDao countryDao, TypeDocDao typeDocDao, DocDao docDao) {
        this.dao = dao;
        this.mapper = mapper;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.typeDocDao = typeDocDao;
        this.docDao = docDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserDtoForSave dto) {
        office = officeDao.loadById(dto.getOfficeId());
        country = countryDao.loadByCode(dto.getCitizenshipCode());
        typeDoc = typeDocDao.loadByName(dto.getDocName());
        user = new User(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(), dto.getPosition(), dto.getPhone(), office, country, dto.isIdentified());
        dao.save(user);
        doc = new Doc(user.getId(), dto.getDocNumber(), dto.getDocDate(), typeDoc, user);
        docDao.save(doc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDtoForListOut> users() {
        List<User> all = dao.all();
        return mapper.mapAll(all, UserDtoForListOut.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDtoForListOut> usersByTerms(UserDtoForListIn dto) {
        return mapper.mapAll(dao.allByTerms(dto), UserDtoForListOut.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto user(Long id) {
        User user = dao.loadById(id);
        if (user == null)
            throw new EntityNotFoundException("User not found");
        return mapper.map(user, UserDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserDtoForUpd dto) {
        user = dao.loadById(dto.getId());
        if (user == null)
            throw new EntityNotFoundException("User not found");
        createDoc(dto);
        docDao.update(doc);
        updUser(dto);
        dao.update(user);
    }

    private void updUser(UserDtoForUpd dto) {
        office = officeDao.loadById(dto.getOfficeId());
        if (office == null)
            throw new EntityNotFoundException("Office not found");
        country = countryDao.loadByCode(dto.getCitizenshipCode());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setPosition(dto.getPosition());
        user.setPhone(dto.getPhone());
        user.setOffice(office);
        user.setCountry(country);
        user.setDoc(doc);
    }

    private void createDoc(UserDtoForUpd dto) {
        typeDoc = typeDocDao.loadByName(dto.getDocName());
        doc = docDao.loadById(dto.getId());
        doc.setTypeDoc(typeDoc);
        doc.setNumber(dto.getDocNumber());
        doc.setDate(dto.getDocDate());
    }

}
