/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.searching;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.Product;
import model.facade.ProductFacade;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author inilog
 */
@Stateless
public class IndexerBean {
    @EJB
    private ProductFacade productFacade;
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;
    
    public void indexing () {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        for (Object prod : productFacade.findAll()) {
            ftem.index((Product)prod);
        }
    }
}
