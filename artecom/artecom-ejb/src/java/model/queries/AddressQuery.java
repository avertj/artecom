/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.queries;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entity.Address;

/**
 *
 * @author donatien
 */
@Stateless
public class AddressQuery {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public Address getAddressByName(String choix) {
        TypedQuery<Address> q = em.createQuery("select distinct OBJECT(a) from Address a where a.name=:choix", Address.class);
        q.setParameter("choix", choix);
        return q.getSingleResult();
    }

}
