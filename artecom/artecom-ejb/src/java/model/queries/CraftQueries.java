/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.queries;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.Craft;

/**
 *
 * @author donatien
 */
public class CraftQueries {
   
   @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;
   
    public Craft getCraft(int choix){
        Query q=em.createQuery("select distinct OBJECT(c) from Craft c where c.id=:choix");
        q.setParameter("choix", choix);
        return (Craft) q.getSingleResult();
    }
}
