/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.Craftsman;

/**
 *
 * @author bmf
 */
@Stateless
public class CraftsmanFacade extends AbstractFacade<Craftsman> {
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CraftsmanFacade() {
        super(Craftsman.class);
    }

}
