/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.entity.Product;
import model.facade.CraftFacade;
import model.facade.CraftsmanFacade;
import model.facade.ProductFacade;

/**
 *
 * @author inilog
 */
@ManagedBean
@RequestScoped
public class rapideAddManagedBean {
    @EJB
    private CraftsmanFacade craftsmanFacade;
    @EJB
    private CraftFacade craftFacade;
    @EJB
    private ProductFacade productFacade;
    private Product p = new Product();

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
    
    public void addProd() {
        productFacade.create(p);
        p = new Product();
        init();
    }
    
    @PostConstruct
    public void init() {
        p.setCraft(craftFacade.find(1));
        p.setWeight(1.0f);
        p.setProducer(craftsmanFacade.find(1));
        p.setPrice(0.0f);
    }

    /**
     * Creates a new instance of rapideAddManagedBean
     */
    public rapideAddManagedBean() {
    }
    
}
