/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.Producer;

/**
 *
 * @author inilog
 */
@Stateless
public class ProducerFacade extends AbstractFacade<Producer> {
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProducerFacade() {
        super(Producer.class);
    }
    
}
