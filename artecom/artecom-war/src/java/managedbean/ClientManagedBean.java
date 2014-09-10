/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.entity.Address;
import model.entity.Client;
import model.entity.User;
import model.facade.ClientFacade;

/**
 *
 * @author donatien
 */
@ManagedBean(name="clientManagedBean")
public class ClientManagedBean {
    @EJB
    private ClientFacade clientFacade;
    
    private Address address;
    
    private User user;
    
    private Client client;
    
    private AddressManagedBean test;
    
    
    public ClientManagedBean(){
        address=new Address();
        user= new User();
        client=new Client();
        
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
}
