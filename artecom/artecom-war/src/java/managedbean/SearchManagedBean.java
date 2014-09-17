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
import model.entity.Site;
import model.facade.ProductFacade;
import model.searching.IndexerBean;
import model.searching.ProductSearchOption;
import model.searching.SearchingBean;
import model.searching.SiteSearchOption;

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
    private List<Site> sites = new ArrayList();
    
    private ProductSearchOption option = new ProductSearchOption();
    private SiteSearchOption optionSite = new SiteSearchOption();
    
    private String mode = "produit";

    public List<Site> getSites() {
        return sites;
    }
    
    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
    
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public SiteSearchOption getOptionSite() {
        return optionSite;
    }

    public void setOptionSite(SiteSearchOption optionSite) {
        this.optionSite = optionSite;
    }

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
        if(mode.equals("produit"))
            products = searchingBean.searchProduct(option);
        if(mode.equals("site"))
            sites = searchingBean.searchSite(optionSite);
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
