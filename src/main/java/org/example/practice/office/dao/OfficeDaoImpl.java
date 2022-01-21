package org.example.practice.office.dao;

import org.example.practice.office.dto.OfficeDtoForListIn;
import org.example.practice.office.entity.Office;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{
    private final EntityManager em;
    private CriteriaBuilder builder;

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

    private CriteriaQuery<Office> buildCriteria(OfficeDtoForListIn dto) {
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> office = criteria.from(Office.class);
        List<Predicate> predicates = new ArrayList<>(4);
        Predicate organization = builder.equal(office.get("organization"), dto.getOrgId());
        predicates.add(organization);
        Predicate active = builder.equal(office.get("active"), dto.isActive());
        predicates.add(active);
        if (dto.getName() != null) {
            Predicate name = builder.equal(office.get("name"), dto.getName());
            predicates.add(name);
        }
        if (dto.getPhone() != null) {
            Predicate phone = builder.equal(office.get("phone"), dto.getPhone());
            predicates.add(phone);
        }
        Predicate[] predicatesArr = new Predicate[predicates.size()];
        predicates.toArray(predicatesArr);
        criteria.where(predicatesArr);
        return criteria;
    }
}
