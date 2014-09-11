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
import model.entity.Site;

/**
 *
 * @author donatien
 */
@Stateless
public class SiteQueries {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public Site getSite(Long choix) {
        TypedQuery<Site> q = em.createQuery("select distinct OBJECT(s) from Site s where s.id=:choix", Site.class);
        q.setParameter("choix", choix);
        return q.getSingleResult();
    }

    public List<Site> getSites() {
        TypedQuery<Site> q = em.createQuery("select distinct OBJECT(s) from Site s", Site.class);
        return q.getResultList();
    }
}
