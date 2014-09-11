/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.entity.Client;
import model.queries.ClientQuery;

/**
 *
 * @author inilog
 */
@ManagedBean(name="findClientManagedBean")
@SessionScoped
public class findClientManagedBean {
    @EJB
    private ClientQuery clientQuery;
    private Client client = new Client();
    private String login;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public void find() {
        client = clientQuery.getClientByLogin(login);
    }

    /**
     * Creates a new instance of findClientManagedBean
     */
    public findClientManagedBean() {
    }
    
}
