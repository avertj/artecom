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
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.entity.Client;
import model.entity.User;
import model.facade.ClientFacade;
import model.facade.UserFacade;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "clientManagedBean")
public class ClientManagedBean {

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;

    private User user;

    private Client client;
    
    private String confirmation;

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public ClientManagedBean() {
        user = new User();
        client = new Client();

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
            try {
                userTransaction.begin();
                user.setLogin(client.getLogin());
                user.setPassword(crypt(user.getPassword()));
                user.setGroupname("client");
                userFacade.create(user);
                clientFacade.create(client);
                userTransaction.commit();
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                context.getExternalContext().redirect(request.getContextPath() + "/client/");
            } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | IOException ex) {
                Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
