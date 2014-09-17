/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.searching;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.Product;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author inilog
 */
@Stateless
public class SearchingBean {
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;
    
    public List<Product> shearchProduct(String word) {
        FullTextEntityManager fullTextEntityManager = 
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Product.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("description")
                .matching(word)
                .createQuery();
        javax.persistence.Query jpaQuery =
            fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);
        return jpaQuery.getResultList();
    }
    
    public List<Product> lessthan (float f, String word) {
        FullTextEntityManager fullTextEntityManager = 
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
//        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
//            .buildQueryBuilder().forEntity(Product.class).get();
//        org.apache.lucene.search.Query luceneQuery = qb.range().onField("price")
//                .from(0).to(i).excludeLimit().createQuery();
        NumericRangeQuery<Float> rangeQuery = NumericRangeQuery.newFloatRange(
                "price", 0f, f, true, true);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Product.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("description")
                .matching(word)
                .createQuery();
        BooleanQuery bq = new BooleanQuery();
        bq.add(rangeQuery, BooleanClause.Occur.MUST);
        bq.add(luceneQuery, BooleanClause.Occur.MUST);

        javax.persistence.Query jpaQuery =
            fullTextEntityManager.createFullTextQuery(bq, Product.class);
        return jpaQuery.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
