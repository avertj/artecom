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
import model.searching.ProductSearchOption;
import model.searching.SearchingBean;

/**
 *
 * @author inilog
 */
@ManagedBean(name = "searchManagedBean")
@RequestScoped
public class SearchManagedBean {
    @EJB
    private SearchingBean searchingBean;
    @EJB
    private IndexerBean indexerBean;
    private List<Product> products = new ArrayList();
    
    private ProductSearchOption option = new ProductSearchOption();

    public ProductSearchOption getOption() {
        return option;
    }

    public void setOption(ProductSearchOption option) {
        this.option = option;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public void indexing() {
        indexerBean.indexing();
    }
    
    public String search() {
//        products = searchingBean.shearchProduct(keyword);
        products = searchingBean.shearchProduct(option);
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
