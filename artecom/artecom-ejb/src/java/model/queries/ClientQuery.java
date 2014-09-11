/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.queries;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.Client;

/**
 *
 * @author inilog
 */
@Stateless
public class ClientQuery {
    @PersistenceContext(unitName = "artecomPU")
    private EntityManager em;

    public Client getClientByLogin (String login) {
        Query q = em.createNamedQuery("Client.getByLogin");
        q.setParameter("login", login);
        return (Client) q.getSingleResult();
    }
}
