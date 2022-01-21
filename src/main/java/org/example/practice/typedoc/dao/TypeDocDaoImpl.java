package org.example.practice.typedoc.dao;

import org.example.practice.typedoc.entity.TypeDoc;
import org.example.practice.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class TypeDocDaoImpl implements TypeDocDao {
    private final EntityManager em;

    @Autowired
    public TypeDocDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TypeDoc> all() {
        TypedQuery<TypeDoc> query = em.createQuery("SELECT t FROM TypeDoc t", TypeDoc.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDoc loadByName(String name) {
        CriteriaQuery<TypeDoc> criteria = buildCriteria(name);
        TypedQuery<TypeDoc> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDoc loadById(Long id) {
        return em.find(TypeDoc.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TypeDoc typeDoc) {
        em.persist(typeDoc);
    }

    private CriteriaQuery<TypeDoc> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDoc> criteria = builder.createQuery(TypeDoc.class);
        Root<TypeDoc> typeDoc = criteria.from(TypeDoc.class);
        criteria.where(builder.equal(typeDoc.get("name"), name));
        return criteria;
    }
}
