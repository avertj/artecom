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
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.entity.Craftsman;
import model.entity.Sale;
import model.facade.SaleFacade;
import model.queries.ClientQuery;
import model.queries.SaleQuery;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "saleManagedBean")
@Named(value = "saleManagedBean")
@ViewScoped
public class SaleManagedBean implements Serializable {

    @EJB
    private SaleQuery saleQuery;
    @EJB
    private ClientQuery clientQuery;

    @ManagedProperty(value = "#{loginManagedBean.login}")
    private String login;

    @EJB
    private SaleFacade saleFacade;

    private Craftsman craftsman;
    private List<Sale> ClientSales;

    private List<Sale> craftsmanSales;

    @PostConstruct
    public void init() {
        craftsman = (Craftsman) clientQuery.getClientByLogin(login);
        craftsmanSales = saleQuery.getSalesCraftById(craftsman.getId());
    }

    public List<Sale> getCraftsmanSales() {
        //craftsman = (Craftsman) clientQuery.getClientByLogin(lg.getLogin());
        //craftsmanSales = saleQuery.getSalesCraftById(craftsman.getId());
        //craftsmanSales = saleFacade.findAll();
        return craftsmanSales;
    }

    public void setCraftsmanSales(List<Sale> craftsmanSales) {
        this.craftsmanSales = craftsmanSales;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleManagedBean() {

    }

    public SaleFacade getSaleFacade() {
        return saleFacade;
    }

    public void setSaleFacade(SaleFacade saleFacade) {
        this.saleFacade = saleFacade;
    }

    public List<Sale> getClientSales() {
        ClientSales = saleFacade.findAll();
        return ClientSales;
    }

    public void setClientSales(List<Sale> ClientSales) {
        this.ClientSales = ClientSales;
    }

    public SaleQuery getSaleQuery() {
        return saleQuery;
    }

    public void setSaleQuery(SaleQuery saleQuery) {
        this.saleQuery = saleQuery;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }

    public ClientQuery getClientQuery() {
        return clientQuery;
    }

    public void setClientQuery(ClientQuery clientQuery) {
        this.clientQuery = clientQuery;
    }

}
