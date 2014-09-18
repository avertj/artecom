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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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

    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;

    private User user;

    private Client client;

    private Client newClient;

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
        newClient = new Client();

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
        client = clientFacade.find((long) 1000); // pour tester
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

        if (!user.getPassword().equals(confirmation)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "confirmation différente du message", null));
        } else {
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

    public void prepareNewClient() {
        client = clientFacade.find((long) 1000);
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setLogin(client.getLogin());
    }

    public void updateClient() {
        client = clientFacade.find((long) 1000);
        System.out.println("Client Name " + newClient.getFirstName());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setLogin(newClient.getLogin());
        clientFacade.edit(client);
    }
}
