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
    private String keyword;
    
    private int max;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public void indexing() {
        indexerBean.indexing();
    }
    
    public String search() {
//        products = searchingBean.shearchProduct(keyword);
        products = searchingBean.lessthan(max, keyword);
        return "search";
    }
    
    @PostConstruct
    public void init() {
    }
    

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
    }
    
    
    
}
