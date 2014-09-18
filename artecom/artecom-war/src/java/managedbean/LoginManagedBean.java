/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author inilog
 */
@ManagedBean(name = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String login;
    private String password;
    private Principal principal;
    private boolean craftsman;

    public boolean getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(boolean craftsman) {
        this.craftsman = craftsman;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
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

    public void login(boolean dontmove) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            String navigateString = "";
            System.out.println(request.getContextPath());
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURL());
            request.login(login, password);
            principal = request.getUserPrincipal();
            if (!dontmove) {
                if (request.isUserInRole("craftsman")) {
                    craftsman = true;
                    navigateString = "/craftsman/";
                } else if (request.isUserInRole("client")) {
                    craftsman = false;
                    navigateString = "/client/";
                }
            }
            try {
                System.out.println(request.getServletPath());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {

            }
        } catch (ServletException e) {

        }
        if (principal == null) {
            login = null;
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            this.login = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath());
        } catch (IOException | ServletException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }

}
