package org.example.practice.user.service;

import org.example.practice.country.dao.CountryDao;
import org.example.practice.country.entity.Country;
import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.entity.Doc;
import org.example.practice.handler.exception.DateParseException;
import org.example.practice.mapper.Mapper;
import org.example.practice.office.dao.OfficeDao;
import org.example.practice.office.entity.Office;
import org.example.practice.typedoc.dao.TypeDocDao;
import org.example.practice.user.dao.UserDao;
import org.example.practice.user.dto.*;
import org.example.practice.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private User updUser;
    private Doc updDoc;


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
        Office office = officeDao.loadById(dto.getOfficeId());
        Country country = null;
        if (dto.getCitizenshipCode() != null)
            country = countryDao.loadByCode(dto.getCitizenshipCode());
        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMiddleName(),
                dto.getPosition(),
                dto.getPhone(),
                office,
                country,
                Boolean.parseBoolean(dto.isIdentified())
        );
        dao.save(user);
        if (dto.getDocName() != null && dto.getDocNumber() != null && dto.getDocDate() != null) {
            Doc doc = new Doc(
                    user.getId(),
                    dto.getDocNumber(),
                    dto.getDocDate(),
                    typeDocDao.loadByName(dto.getDocName()),
                    user
            );
            docDao.save(doc);
        }
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
        return mapper.map(user, UserDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserDtoForUpd dto) {
        updUser = dao.loadById(dto.getId());
        updDoc = updUser.getDoc();
        if (validateDoc(dto))
            updDoc(dto);
        updUser(dto);
        dao.update(updUser);
    }

    /**
     * Обновление данных User
     * @param dto
     */
    private void updUser(UserDtoForUpd dto) {
        updUser.setFirstName(dto.getFirstName());
        updUser.setPosition(dto.getPosition());
        if (dto.isIdentified() != null)
            updUser.setIdentified(Boolean.parseBoolean(dto.isIdentified()));
        if (dto.getLastName() != null)
            updUser.setLastName(dto.getLastName());
        if (dto.getMiddleName() != null)
            updUser.setMiddleName(dto.getMiddleName());
        if (dto.getPhone() != null)
            updUser.setPhone(dto.getPhone());
        if (dto.getOfficeId() != null)
            updUser.setOffice(officeDao.loadById(dto.getOfficeId()));
        if (dto.getCitizenshipCode() != null)
            updUser.setCountry(countryDao.loadByCode(dto.getCitizenshipCode()));
        if (updDoc != null) {
            updUser.setDoc(updDoc);
        }
    }

    /**
     * Обновление данных документа
     * @param dto
     */
    private void updDoc(UserDtoForUpd dto) {
        if (dto.getDocName() != null)
            updDoc.setTypeDoc(typeDocDao.loadByName(dto.getDocName()));
        if (dto.getDocNumber() != null)
            updDoc.setNumber(dto.getDocNumber());
        if (dto.getDocDate() != null) {
            updDoc.setDate(dto.getDocDate());
        }
        docDao.update(updDoc);
    }

    /**
     * Валидация данных и создание нового документа, в случае его отсутствия
     * @param dto
     * @return boolean
     */
    private boolean validateDoc(UserDtoForUpd dto) {
        if (updDoc == null) {
            if ((dto.getDocName() == null) || (dto.getDocNumber() == null) || (dto.getDocDate() == null)) {
                return false;
            }
            updDoc = new Doc(dto.getId(), updUser);
        }
        return true;
    }
}
