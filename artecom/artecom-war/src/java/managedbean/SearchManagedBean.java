/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.entity.Product;
import model.facade.ProductFacade;
import model.searching.IndexerBean;
import model.searching.SearchingBean;

/**
 *
 * @author inilog
 */
@ManagedBean(name = "searchManagedBean")
@RequestScoped
public class SearchManagedBean {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private SearchingBean searchingBean;
    @EJB
    private IndexerBean indexerBean;
    private List<Product> products = new ArrayList();
    private String word;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public void indexing() {
        indexerBean.indexing();
    }
    
    public void recherche() {
        products = searchingBean.shearchProduct(word);
    }
    
    @PostConstruct
    public void init() {
//        this.products = productFacade.findAll();
    }
    

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
    }
    
    
    
}
