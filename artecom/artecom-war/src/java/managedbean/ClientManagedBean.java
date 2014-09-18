/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.entity.Address;
import model.entity.Client;
import model.entity.User;
import model.facade.AddressFacade;
import model.facade.ClientFacade;
import model.facade.UserFacade;
import model.queries.ClientQuery;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "clientManagedBean")
@SessionScoped
public class ClientManagedBean {
    
    @EJB
    private AddressFacade addressFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;
    
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg;

    @EJB
    private ClientQuery clientQuery;
   

    private User user;

    private Client client;
    
    private Client newClient ;
    
    private String confirmation;
    
    private List<Address> addresses ;
    
    private Address addr ;
    
    private Address selectedAddr ;
    
    private int editposition;

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public ClientManagedBean() {
        user = new User();
        client = new Client();
        newClient = new Client();
        
        addr = new Address();
        selectedAddr = new Address();
    }

    // Address Getter-setter
    
    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    public void setAddressFacade(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    public List<Address> getAddresses() {
        addresses = getDisplay().getAddress();
        return addresses;
        
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
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

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    
    public String crypt(String pass) {
        MessageDigest md;
        try {
            md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return Base64.encodeBase64String(passwordDigest);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void add() {
        
        if(!user.getPassword().equals(confirmation))
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "confirmation différente du message", null));
        else {
            user.setLogin(client.getLogin());
            user.setPassword(crypt(user.getPassword()));
            user.setGroupname("client");
            try {
                userFacade.create(user);
                clientFacade.create(client);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "email existant!", null));
                return;
            }
            
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                context.getExternalContext().redirect(request.getContextPath() + "/client/");
            } catch (SecurityException | IllegalStateException | IOException ex) {
                Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Client getDisplay()
    {
        String login = lg.getLogin();
        client = clientQuery.getClientByLogin(login); 
        return client ;
    }
    
    public void prepareNewClient()
    {
        client  = getDisplay();
        System.out.println("Prepare: "+client.getLogin());
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setLogin(client.getLogin());        
    }
    
    public void updateClient()
    {   
        client  = getDisplay();
        System.out.println("Client Name "+ newClient.getFirstName());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setLogin(newClient.getLogin());
        System.out.println("Client Updated : "+client.getFirstName());
        clientFacade.edit(client);    
    }
    /// Address Function
    public void addAddress()
    {
        
        client = getDisplay();
        addressFacade.create(addr);
        System.out.println(addr.getId() + " " + addr.getName());
        
        client.addAddress(addr);
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
