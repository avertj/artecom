/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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

        String prodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prodId");
        String qty = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("qty");

        Product p = productFacade.find(Long.valueOf(prodId));
        System.out.println("Adding " + p.getId() + " to cart.");
        cart.add(new ProductQuantity(p, Integer.valueOf(qty)));

    }

    public void removeClick() {

        String prodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prodId");
    }
}
