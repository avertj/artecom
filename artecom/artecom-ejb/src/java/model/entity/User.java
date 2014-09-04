/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author inilog
 */
@Entity
@Table(name = "UserApli")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String login;
    private String password;
    
    @OneToOne
    private Client profil;

    public Client getProfil() {
        return profil;
    }

    public void setProfil(Client profil) {
        this.profil = profil;
    }
    
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "model.entity.User[ id=" + login + " ]";
    }

}
