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
import model.entity.Site;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;

/**
 *
 * @author inilog
 */
@Stateless
public class SearchingBean {
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;
    
    public List<Product> searchProduct (ProductSearchOption option) {
        FullTextEntityManager fullTextEntityManager = 
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        BooleanQuery bq = new BooleanQuery();
        
        //recherche par mot clef
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Product.class).get();
        org.apache.lucene.search.Query keywordQuery;
        if (!option.getKeyword().equals("")) {
            keywordQuery = qb
                    .keyword()
                    .onFields("description")
                    .matching(option.getKeyword())
                    .createQuery();
            if (option.isKeywordRequiered())
                bq.add(keywordQuery, BooleanClause.Occur.MUST);
            else
                bq.add(keywordQuery, BooleanClause.Occur.SHOULD);
        }
        
        //valeur prix max
        NumericRangeQuery<Float> priceQuery = NumericRangeQuery.newFloatRange(
                "price", 0f, option.getPrixMax(), true, true);
        if (option.getPrixMax()>=0.01f)
            bq.add(priceQuery, BooleanClause.Occur.MUST);

        javax.persistence.Query jpaQuery =
            fullTextEntityManager.createFullTextQuery(bq, Product.class);
        return jpaQuery.getResultList();
    }
    
    public List<Site> searchSite (SiteSearchOption option) {
        FullTextEntityManager fullTextEntityManager = 
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity( Site.class ).get();

          org.apache.lucene.search.Query luceneQuery = builder.spatial()
            .onDefaultCoordinates()
            .within(option.getDist(), Unit.KM )
            .ofLatitude( option.getLat() )
            .andLongitude( option.getLon() )
            .createQuery();   
        javax.persistence.Query jpaQuery =
            fullTextEntityManager.createFullTextQuery(luceneQuery, Site.class);
        return jpaQuery.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
