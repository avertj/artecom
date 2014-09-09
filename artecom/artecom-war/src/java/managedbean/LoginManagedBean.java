/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.security.Principal;
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
public class LoginManagedBean {

    private String login;
    private String password;

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

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            String navigateString = "";
            request.login(login, password);
            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("admin")) {
                navigateString = "/admin/";
            } else if (request.isUserInRole("craftsman")) {
                navigateString = "/craftsman/";
            } else if (request.isUserInRole("client")) {
                navigateString = "/client/";
            }
            try {
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {

            }
        } catch (ServletException e) {

        }
    }

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }

}
