/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.queries;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entity.Craft;

/**
 *
 * @author donatien
 */
@Stateless
public class CraftQueries {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public Craft getCraft(Long choix) {
        TypedQuery<Craft> q = em.createQuery("select distinct OBJECT(c) from Craft c where c.id=:choix", Craft.class);
        q.setParameter("choix", choix);
        return q.getSingleResult();
    }

    public List<Craft> getRootCrafts() {
        TypedQuery<Craft> q = em.createQuery("select distinct OBJECT(c) from Craft c where c.parent = NULL", Craft.class);
        return q.getResultList();
    }

    public List<Craft> getSubCrafts(Craft c) {
        TypedQuery<Craft> q = em.createQuery("select distinct OBJECT(c) from Craft c where c.parent=:p", Craft.class);
        q.setParameter("p", c);
        return q.getResultList();
    }
}
