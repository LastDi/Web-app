package org.example.practice.organization.dao;

import org.example.practice.organization.dto.OrganizationDtoForListIn;
import org.example.practice.organization.entity.Organization;
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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;
    private CriteriaBuilder builder;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
        this.builder = em.getCriteriaBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> allByTerms(OrganizationDtoForListIn dto) {
        CriteriaQuery<Organization> criteria = buildCriteria(dto);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<Organization> buildCriteria(OrganizationDtoForListIn dto) {
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organization = criteria.from(Organization.class);
        List<Predicate> predicates = new ArrayList<>(3);
        Predicate name = builder.equal(organization.get("name"), dto.getName());
        predicates.add(name);
        Predicate active = builder.equal(organization.get("active"), dto.isActive());
        predicates.add(active);
        if (dto.getInn() != null) {
            Predicate inn = builder.equal(organization.get("inn"), dto.getInn());
            predicates.add(inn);
        }
        Predicate[] predicatesArr = new Predicate[predicates.size()];
        predicates.toArray(predicatesArr);
        criteria.where(predicatesArr);
        return criteria;
    }
}
