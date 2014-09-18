/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.queries;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.entity.ProductQuantity;
import model.entity.Sale;

/**
 *
 * @author donatien
 */
@Stateless
public class SaleQuery {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public List<Sale> getSalesCraftById(Long id) {
        TypedQuery<ProductQuantity> pqq = em.createQuery("SELECT OBJECT(pq) FROM ProductQuantity pq WHERE pq.product.craftsman.id=:idq", ProductQuantity.class);
        pqq.setParameter("idq", id);
        List<ProductQuantity> lpq = pqq.getResultList();
        List<Sale> ls = new ArrayList<>();
        //System.out.println(lpq.size());
        for (ProductQuantity pq : lpq) {
            TypedQuery<Sale> tq = em.createQuery("SELECT OBJECT(s) FROM Sale s WHERE :id MEMBER OF s.products", Sale.class);
            tq.setParameter("id", pq.getId());
            Sale s = tq.getSingleResult();
            if (!ls.contains(s)) {
                ls.add(s);
            }
        }
        //System.out.println(ls.size());
        return ls;
    }

    public List<Sale> getSalesClientById(Long id) {
        Query q = em.createQuery("select OBJECT(s) from Sale s where s.client.id=:idq");
        q.setParameter("idq", id);
        return q.getResultList();
    }
}
