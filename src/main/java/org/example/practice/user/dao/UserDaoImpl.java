package org.example.practice.user.dao;

import org.example.practice.user.dto.UserDtoForListIn;
import org.example.practice.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager em;
    private CriteriaBuilder builder;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
        builder = em.getCriteriaBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> all() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> allByTerms(UserDtoForListIn dto) {
        CriteriaQuery<User> criteria = buildCriteria(dto);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }

    private CriteriaQuery<User> buildCriteria(UserDtoForListIn dto) {
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        List<Predicate> predicates = new ArrayList<>(7);
        Predicate office = builder.equal(user.get("office"), dto.getOfficeId());
        predicates.add(office);
        if (dto.getFirstName() != null) {
            Predicate firstName = builder.equal(user.get("firstName"), dto.getFirstName());
            predicates.add(firstName);
        }
        if (dto.getLastName() != null) {
            Predicate lastName = builder.equal(user.get("lastName"), dto.getLastName());
            predicates.add(lastName);
        }
        if (dto.getMiddleName() != null) {
            Predicate middleName = builder.equal(user.get("middleName"), dto.getMiddleName());
            predicates.add(middleName);
        }
        if (dto.getPosition() != null) {
            Predicate position = builder.equal(user.get("position"), dto.getPosition());
            predicates.add(position);
        }
        if (dto.getDocCode() != null) {
            Predicate doc = builder.equal(user.get("doc"), dto.getDocCode());
            predicates.add(doc);
        }
        if (dto.getCitizenshipCode() != null) {
            Predicate countryCode = builder.equal(user.get("citizenshipCode"), dto.getCitizenshipCode());
            predicates.add(countryCode);
        }
        Predicate[] predicatesArr = new Predicate[predicates.size()];
        predicates.toArray(predicatesArr);
        criteria.where(predicatesArr);
        return criteria;
    }


}
