package org.example.practice.user.service;

import org.example.practice.country.dao.CountryDao;
import org.example.practice.country.entity.Country;
import org.example.practice.doc.dao.DocDao;
import org.example.practice.doc.entity.Doc;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
                office, country,
                dto.isIdentified()
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
            user.setIdentified(true);
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
        user = dao.loadById(dto.getId());
        doc = user.getDoc();
        if (validateDoc(dto))
            updDoc(dto);
        updUser(dto);
        dao.update(user);
    }

    /**
     * Обновление данных User
     * @param dto
     */
    private void updUser(UserDtoForUpd dto) {
        user.setFirstName(dto.getFirstName());
        user.setPosition(dto.getPosition());
        if (dto.getLastName() != null)
            user.setLastName(dto.getLastName());
        if (dto.getMiddleName() != null)
            user.setMiddleName(dto.getMiddleName());
        if (dto.getPhone() != null)
            user.setPhone(dto.getPhone());
        if (dto.getOfficeId() != null)
            user.setOffice(officeDao.loadById(dto.getOfficeId()));
        if (dto.getCitizenshipCode() != null)
            user.setCountry(countryDao.loadByCode(dto.getCitizenshipCode()));
        if (doc != null) {
            user.setDoc(doc);
            user.setIdentified(true);
        }
    }

    /**
     * Обновление данных документа
     * @param dto
     */
    private void updDoc(UserDtoForUpd dto) {
        if (dto.getDocName() != null)
            doc.setTypeDoc(typeDocDao.loadByName(dto.getDocName()));
        if (dto.getDocNumber() != null)
            doc.setNumber(dto.getDocNumber());
        if (dto.getDocDate() != null)
            doc.setDate(dto.getDocDate());
        docDao.update(doc);
    }

    /**
     * Валидация данных и создание нового документа, в случае его отсутствия
     * @param dto
     * @return boolean
     */
    private boolean validateDoc(UserDtoForUpd dto) {
        if (doc == null) {
            if (dto.getDocName() != null && dto.getDocNumber() != null && dto.getDocDate() != null) {
                doc = new Doc(dto.getId(), user);
            }
            else {
                return false;
            }
        }
        return true;
    }
}
