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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.entity.Address;
import model.entity.Client;
import model.entity.User;
import model.facade.AddressFacade;
import model.facade.ClientFacade;
import model.facade.UserFacade;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author donatien
 */
@ManagedBean(name="clientManagedBean")
public class ClientManagedBean {
    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;
    
    private User user;
    
    private Client client;
    
    
    public ClientManagedBean(){
        user= new User();
        client=new Client();
        
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
    
    public String crypt (String pass) {
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
        user.setLogin(client.getLogin());
        user.setPassword(crypt(user.getPassword()));
        user.setGroupname("client");
        userFacade.create(user);
        clientFacade.create(client);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(user.getLogin(), user.getPassword());
            context.getExternalContext().redirect(request.getContextPath() + "/client/");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
