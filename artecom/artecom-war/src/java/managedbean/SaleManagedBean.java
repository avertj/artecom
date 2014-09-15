/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.entity.Client;
import model.entity.Sale;
import model.facade.SaleFacade;
import model.queries.ClientQuery;
import model.queries.SaleQuery;

/**
 *
 * @author donatien
 */
@ManagedBean(name="saleManagedBean")
@SessionScoped
public class SaleManagedBean implements Serializable{
    @EJB
    private SaleFacade saleFacade;
    
    private List<Sale> sales ;
    
    @EJB
    private SaleQuery saleQuery;
    
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg;

    public LoginManagedBean getLg() {
        return lg;
    }

    public void setLg(LoginManagedBean lg) {
        this.lg = lg;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String login;
    
    private Long id;
    
    @EJB
    private ClientQuery cq;

    public ClientQuery getCq() {
        return cq;
    }

    public void setCq(ClientQuery cq) {
        this.cq = cq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public SaleManagedBean(){
    
    }
    public SaleFacade getSaleFacade() {
        return saleFacade;
    }

    public void setSaleFacade(SaleFacade saleFacade) {
        this.saleFacade = saleFacade;
    }

    public List<Sale> getSales() {
        Client cl=cq.getClientByLogin("bob@gmail.com");
        if(cl!=null){
            sales= saleQuery.getSalesById(cl.getId());
        }
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    

    public SaleQuery getSaleQuery() {
        return saleQuery;
    }

    public void setSaleQuery(SaleQuery saleQuery) {
        this.saleQuery = saleQuery;
    }
    
}
