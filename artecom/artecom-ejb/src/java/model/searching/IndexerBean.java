/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.searching;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author inilog
 */
@Stateless
public class IndexerBean {

    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public void indexing() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(IndexerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
