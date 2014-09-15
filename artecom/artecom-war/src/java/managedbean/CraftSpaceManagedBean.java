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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.entity.Client;
import model.entity.Craftsman;
import model.entity.Sale;
import model.entity.User;
import model.facade.ClientFacade;
import model.facade.CraftsmanFacade;
import model.facade.SaleFacade;
import model.facade.UserFacade;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author donatien
 */
@ManagedBean(name="craftSpaceManagedBean")
@SessionScoped
public class CraftSpaceManagedBean {
    //liste de toutes les commandes
    private List<Sale> sales ;
    private SaleFacade saleFacade;
    private Sale sale;
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private CraftsmanFacade craftsmanFacade;

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public CraftsmanFacade getCraftsmanFacade() {
        return craftsmanFacade;
    }

    public void setCraftsmanFacade(CraftsmanFacade craftsmanFacade) {
        this.craftsmanFacade = craftsmanFacade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }

    private User user;

    private Craftsman craftsman;

    public CraftSpaceManagedBean() {
        user= new User();
        craftsman= new Craftsman();
    }


    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    public List<Sale> getSales() {
        sales=saleFacade.findAll();
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    public SaleFacade getSaleFacade() {
        return saleFacade;
    }

    public void setSaleFacade(SaleFacade saleFacade) {
        this.saleFacade = saleFacade;
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
        user.setLogin(craftsman.getLogin());
        user.setPassword(crypt(user.getPassword()));
        user.setGroupname("craftsman");
        userFacade.create(user);
        craftsmanFacade.create(craftsman);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(user.getLogin(), user.getPassword());
            context.getExternalContext().redirect(request.getContextPath() + "/craftsman/");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
