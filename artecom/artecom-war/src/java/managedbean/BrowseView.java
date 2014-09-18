/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entity.Craft;
import model.entity.Product;
import model.queries.CraftQueries;
import model.queries.ProductQueries;

/**
 *
 * @author bmf
 */
@Named(value = "browseView")
@ViewScoped
public class BrowseView implements Serializable {

    @EJB
    private CraftQueries craftQueries;
    @EJB
    private ProductQueries productQueries;

    private List<Product> productList;

    private List<Craft> crafts;

    public List<Craft> getCrafts() {
        if (crafts == null) {
            crafts = craftQueries.getCrafts();
        }
        return crafts;
    }

    public void setCrafts(List<Craft> crafts) {
        this.crafts = crafts;
    }

    public List<Product> getProducts() {
        return productQueries.getFilteredProducts(getCrafts(), 0f, 10f);
    }

    public void categoryClick(ActionEvent evt) {

    }

    public void valueChanged(ValueChangeEvent evt) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/search.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BrowseView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
