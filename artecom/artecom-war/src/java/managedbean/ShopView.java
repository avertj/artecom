/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
@Named(value = "shopView")
@ViewScoped
public class ShopView {

    @EJB
    private CraftQueries craftQueries;
    @EJB
    private ProductQueries productQueries;

    private Craft precedentCraft;
    private Craft currentCraft;

    private List<Product> productList;

    public ShopView() {
    }

    public List<Craft> getCrafts() {
        if (currentCraft == null) {
            return craftQueries.getRootCrafts();
        } else {
            List<Craft> list = craftQueries.getSubCrafts(currentCraft);
            list.add(0, precedentCraft);
            return list;
        }
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void categoryClick(ActionEvent actionEvent) {
        String catId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catId");
        //System.out.println(catId);
        precedentCraft = currentCraft;
        currentCraft = craftQueries.getCraft(Long.valueOf(catId));
        productList = productQueries.getProducts(currentCraft);
    }

}
