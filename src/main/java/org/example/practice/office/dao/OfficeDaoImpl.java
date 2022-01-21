package org.example.practice.office.dao;

import org.example.practice.office.dto.OfficeDtoForListIn;
import org.example.practice.office.entity.Office;
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

@Repository
public class OfficeDaoImpl implements OfficeDao{
    private final EntityManager em;
    private CriteriaBuilder builder;
    private Map<String, Supplier<?>> map;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
        this.builder = em.getCriteriaBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        TypedQuery<Office> query = em.createQuery("Select o FROM Office o", Office.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> allByTerms(OfficeDtoForListIn dto) {
        CriteriaQuery<Office> criteria = buildCriteria(dto);
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        em.merge(office);
    }

    private void initMap(OfficeDtoForListIn dto) {
        if (map == null)
            map = new HashMap<>();
        map.put("organization", dto::getOrgId);
        map.put("active", dto::isActive);
        map.put("name", dto::getName);
        map.put("phone", dto::getPhone);
    }

    private CriteriaQuery<Office> buildCriteria(OfficeDtoForListIn dto) {
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> office = criteria.from(Office.class);
        initMap(dto);
        List<Predicate> predicatesList = map.entrySet().stream()
                .filter(pair -> pair.getValue().get() != null)
                .map(pair -> builder.equal(office.get(pair.getKey()), pair.getValue().get()))
                .toList();
        Predicate[] predicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(predicates);
        criteria.where(predicates);
        return criteria;
    }
}
