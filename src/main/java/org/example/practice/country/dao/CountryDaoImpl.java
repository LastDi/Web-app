package org.example.practice.country.dao;

import org.example.practice.country.entity.Country;
import org.example.practice.handler.exception.EntityNotFoundException;
import org.example.practice.user.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao{
    private final EntityManager em;

    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> all() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadById(Long id) {
        return em.find(Country.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadByCode(String code) {
        CriteriaQuery<Country> criteria = buildCriteria(code);
        TypedQuery<Country> query = em.createQuery(criteria);
        Country country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Country not found");
        }
        return country;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Country country) {
        em.persist(country);
    }

    private CriteriaQuery<Country> buildCriteria(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> country = criteria.from(Country.class);
        criteria.where(builder.equal(country.get("code"), code));
        return criteria;
    }
}
