/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import model.entity.Cart;
import model.entity.Product;
import model.entity.ProductQuantity;
import model.facade.ProductFacade;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "cartManager")
@SessionScoped
public class CartManager implements Serializable {

    @EJB
    private ProductFacade productFacade;

    private Cart cart;

    public CartManager() {
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void addClick(ActionEvent actionEvent) {
        System.out.println("add");
        String prodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prodId");
        String qty = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("qty");

        Product p = productFacade.find(Long.valueOf(prodId));
        cart.add(new ProductQuantity(p, Integer.valueOf(qty)));

    }

    public void removeClick(ActionEvent actionEvent) {
        String prodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prodId");
        Product p = productFacade.find(Long.valueOf(prodId));
        cart.remove(new ProductQuantity(p, 0));
    }

    public void updateClick(AjaxBehaviorEvent evt) {
        String prodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prodId");
        Integer qty = (Integer) evt.getComponent().getAttributes().get("value");
        Product p = productFacade.find(Long.valueOf(prodId));
        cart.updateQuantity(new ProductQuantity(p, 0));
    }

    public void checkout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("client/checkout.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BrowseView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
