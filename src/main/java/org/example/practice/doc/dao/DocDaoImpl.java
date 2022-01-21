package org.example.practice.doc.dao;

import org.example.practice.doc.entity.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocDaoImpl implements DocDao{

    private final EntityManager em;

    @Autowired
    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Doc> all() {
        TypedQuery<Doc> query = em.createQuery("SELECT d FROM Doc d", Doc.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doc loadById(Long id) {
        return em.find(Doc.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doc loadByNumber(String number) {
        CriteriaQuery<Doc> criteria = builtCriteria("number");
        TypedQuery<Doc> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Doc doc) {
        em.persist(doc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Doc doc) {
        em.merge(doc);
    }

    private CriteriaQuery<Doc> builtCriteria(String number) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Doc> criteria = cb.createQuery(Doc.class);
        Root<Doc> docs = criteria.from(Doc.class);
        criteria.where(cb.equal(docs.get("number"), number));
        return criteria;

    }
}
