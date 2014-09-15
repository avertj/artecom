/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ShopView implements Serializable {

    @EJB
    private CraftQueries craftQueries;
    @EJB
    private ProductQueries productQueries;

    private Craft currentCraft;

    private List<Product> productList;

    public ShopView() {
    }

    public List<Craft> getCrafts() {
        if (currentCraft == null) {
            return craftQueries.getRootCrafts();
        } else {
            List<Craft> list = craftQueries.getSubCrafts(currentCraft);
            return list;
        }
    }

    public List<Craft> getBreadcrumbs() {
        if (currentCraft == null || currentCraft.getParent() == null) {
            return null;
        }
        List<Craft> bread = new ArrayList<>();
        Craft tmp = currentCraft;
        while ((tmp = tmp.getParent()) != null) {
            bread.add(tmp);
        }
        return Lists.reverse(bread);
    }

    public Craft getCurrentCraft() {
        return currentCraft;
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void categoryClick(ActionEvent actionEvent) {
        String catId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catId");
        if (catId == null || catId.equalsIgnoreCase("null")) {
            currentCraft = null;
            productList = null;
        } else {
            currentCraft = craftQueries.getCraft(Long.valueOf(catId));
            productList = productQueries.getProducts(currentCraft);
        }
    }

}
