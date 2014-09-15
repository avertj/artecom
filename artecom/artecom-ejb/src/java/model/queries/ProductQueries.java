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
import model.entity.Product;

/**
 *
 * @author donatien
 */
@Stateless
public class ProductQueries {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public Product getProduct(Long choix) {
        TypedQuery<Product> q = em.createQuery("select distinct OBJECT(p) from Product p where p.id=:choix", Product.class);
        q.setParameter("choix", choix);
        return q.getSingleResult();
    }

    public List<Product> getProductsByCraft(Craft c) {
        TypedQuery<Product> q = em.createQuery("select distinct OBJECT(p) from Product p where p.craft=:c", Product.class);
        q.setParameter("c", c);
        return q.getResultList();
    }
    
    public List<Product> getProductsById(Long c) {
        TypedQuery<Product> q = em.createQuery("select distinct OBJECT(p) from Product p where p.craftsman.id=:c", Product.class);
        q.setParameter("c", c);
        return q.getResultList();
    }
}
