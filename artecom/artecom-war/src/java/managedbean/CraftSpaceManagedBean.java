/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.entity.Sale;
import model.facade.SaleFacade;

/**
 *
 * @author donatien
 */
@ManagedBean(name="craftSpaceManagedBean")
@SessionScoped
public class CraftSpaceManagedBean {
    //liste de toutes les commandes
    private List<Sale> sales ;
    private SaleFacade saleFacade;
    private Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    public CraftSpaceManagedBean(){
           sale=new Sale();
    }
    public List<Sale> getSales() {
        sales=saleFacade.findAll();
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    public SaleFacade getSaleFacade() {
        return saleFacade;
    }

    public void setSaleFacade(SaleFacade saleFacade) {
        this.saleFacade = saleFacade;
    }
}
