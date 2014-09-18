/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import model.entity.Address;
import model.entity.Client;
import model.facade.AddressFacade;
import model.facade.ClientFacade;
import model.queries.ClientQuery;

/**
 *
 * @author Nguyen Bao
 */
@Named(value = "clientAddressManagedBean")

public class ClientAddressManagedBean {
    @EJB
    private AddressFacade addressFacade;
    @EJB
    private ClientFacade clientFacade;
    
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg;

    @EJB
    private ClientQuery clientQuery;
   


    private long clientId;
    
    private Client client ;
    
    private List<Address> addresses ;
    
    private Address addr ;
    
    private Address selectedAddr ;
    
    private int editposition;
    
    public ClientAddressManagedBean() {
        
        clientId =(long)1000;
        client = new Client();
        addr = new Address();
        selectedAddr = new Address();
              
    }

    public Address getSelectedAddr() {
        return selectedAddr;
    }

    public void setSelectedAddr(Address selectedAddr) {
        this.selectedAddr = selectedAddr;
    }

    public int getEditposition() {
        return editposition;
    }

    public void setEditposition(int editposition) {
        this.editposition = editposition;
    }
    
    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }



    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    public void setAddressFacade(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Address> getAddresses() {
        
        addresses = getDisplay().getAddress();
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public LoginManagedBean getLg() {
        return lg;
    }

    public void setLg(LoginManagedBean lg) {
        this.lg = lg;
    }

    public ClientQuery getClientQuery() {
        return clientQuery;
    }

    public void setClientQuery(ClientQuery clientQuery) {
        this.clientQuery = clientQuery;
    }
    
    
    
    public Client getDisplay()
    {
        String login = lg.getLogin();
        System.out.println(login);
        client = clientQuery.getClientByLogin(login); 
        System.out.println(client.getFirstName());
        return client ;
    }
   
    public void addAddress()
    {
        
        client = getDisplay();
        System.out.println("before "+client.getFirstName()+" "+client.getAddress().size());
        addressFacade.create(addr);
        System.out.println(addr.getId() + " " + addr.getName());
        
        client.addAddress(addr);
        System.out.println("after :"+client.getAddress().size());
        clientFacade.edit(client);
        addr = null ;

    }
    
     public void removeSelectedAddress()
    {
        client = getDisplay();
        System.out.println("before "+client.getAddress().size());
        client.removeAddress(selectedAddr);
        System.out.println("after :"+client.getAddress().size());
        clientFacade.edit(client);
    
    }  
    public void selectedPosition()
    {
        if(addresses.contains(selectedAddr))
        {
            editposition = addresses.indexOf(selectedAddr);
            System.out.println(editposition);
        }
    }     
    public void updateAddress()
    {
       
      client = getDisplay();
      addressFacade.edit(selectedAddr);
      client.updateAddress(editposition, selectedAddr);
      
      clientFacade.edit(client);
      addresses = client.getAddress();
      System.out.println("After : "+selectedAddr.getName() + " " + addresses.get(editposition).getCity());
      
    }   

}
