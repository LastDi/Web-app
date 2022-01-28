package org.example.practice.user.dao;

import org.example.practice.handler.exception.EntityNotFoundException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager em;
    private CriteriaBuilder builder;
    private Map<String, Supplier<?>> map;

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
        User user = em.find(User.class, id);
        if (user == null)
            throw new EntityNotFoundException("User not found");
        return user;
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

    private void initMap(UserDtoForListIn dto) {
        if (map == null)
            map = new HashMap<>();
        map.put("office", dto::getOfficeId);
        map.put("firstName", dto::getFirstName);
        map.put("lastName", dto::getLastName);
        map.put("middleName", dto::getMiddleName);
        map.put("position", dto::getPosition);
        map.put("doc", dto::getDocCode);
        map.put("citizenshipCode", dto::getCitizenshipCode);
    }

    private CriteriaQuery<User> buildCriteria(UserDtoForListIn dto) {
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        initMap(dto);
        List<Predicate> predicatesList = map.entrySet().stream()
                .filter(pair -> pair.getValue().get() != null)
                .map(pair -> builder.equal(user.get(pair.getKey()), pair.getValue().get()))
                .toList();
        Predicate[] predicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(predicates);
        criteria.where(predicates);
        return criteria;
    }


}
