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
import javax.persistence.Query;
import model.entity.Sale;

/**
 *
 * @author donatien
 */
@Stateless
public class SaleQuery {
    
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;
    
    public List<Sale> getSalesById(Long id){
       Query q=em.createQuery("select OBJECT(s) from Sale s where s.cart.products.product.craftsman.id:=idq");
       q.setParameter("idq", id);
       return q.getResultList(); 
    }
}
