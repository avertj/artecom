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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.entity.Address;
import model.entity.Cart;
import model.entity.Client;
import model.entity.ProductQuantity;
import model.entity.Sale;
import model.facade.ClientFacade;
import model.facade.ProductQuantityFacade;
import model.facade.SaleFacade;
import model.queries.ClientQuery;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "checkoutView")
@Named(value = "checkoutView")
@ViewScoped
public class CheckoutView implements Serializable {

    @EJB
    private ClientQuery clientQuery;
    @EJB
    private SaleFacade saleFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private ProductQuantityFacade pqFacade;

    @ManagedProperty(value = "#{loginManagedBean.login}")
    private String login;
    @ManagedProperty(value = "#{cartManager.cart}")
    private Cart cart;

    private Client client;
    private Address shippingAddress;
    private Address billingAddress;

    private String cardNumber;
    private String securityCode;
    private Long expirationMonth;
    private Long expirationYear;

    public CheckoutView() {
    }

    @PostConstruct
    public void init() {

        client = clientQuery.getClientByLogin(login);
        System.out.println(client);
        //client.getAddresses();
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<Address> getAddresses() {
        return client.getAddresses();
    }

    public void add() {
        Sale s = new Sale();
        s.setBillingAdress(billingAddress);
        s.setShippingAdress(shippingAddress);
        s.setClient(client);
        s.setPrice(cart.getPrice());
        for (ProductQuantity pq : cart.getList()) {
            pqFacade.create(pq);
        }
        s.setProducts(cart.getList());
        s.setStatus(Sale.Status.WAITING_FOR_SHIPMENT);
        saleFacade.create(s);
        client.setLastBillingAdress(billingAddress.getId());
        client.setLastShippingAdress(shippingAddress.getId());
        clientFacade.edit(client);
        cart.empty();
    }

    public String onFlowProcess(FlowEvent event) {
        String current = event.getOldStep();
        String next = event.getNewStep();
        boolean proceed = true;
        if (current.equals("shipping") && (shippingAddress == null)) {
            FacesMessage facesMessage = new FacesMessage("Vous devez choisir une adresse");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            proceed = false;
        } else if (current.equals("billing") && (billingAddress == null)) {
            FacesMessage facesMessage = new FacesMessage("Vous devez choisir une adresse");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            proceed = false;
        }
        return proceed ? next : current;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Client getClient() {
        return client;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Long expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Long getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Long expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
