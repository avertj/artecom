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
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.entity.Client;
import model.entity.Craftsman;
import model.entity.Sale;
import model.entity.User;
import model.facade.CraftsmanFacade;
import model.facade.SaleFacade;
import model.facade.UserFacade;
import model.queries.ClientQuery;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "craftSpaceManagedBean")
@SessionScoped
public class CraftSpaceManagedBean {

    //liste de toutes les commandes
    private List<Sale> sales;
    private SaleFacade saleFacade;
    private Sale sale;
    private String confirmation;

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    @EJB
    private UserFacade userFacade;
    @EJB
    private CraftsmanFacade craftsmanFacade;

    private Boolean editMode = false;

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

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
        user = new User();
        craftsman = new Craftsman();
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<Sale> getSales() {
        sales = saleFacade.findAll();
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
            Logger.getLogger(CraftSpaceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void add() {
        if (!user.getPassword().equals(confirmation)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "confirmation différente du message", null));
        } else {
            user.setLogin(craftsman.getLogin());
            user.setPassword(crypt(user.getPassword()));
            user.setGroupname("craftsman");
            try {
                userFacade.create(user);
                craftsmanFacade.create(craftsman);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "email existant!", null));
                return;
            }

            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                context.getExternalContext().redirect(request.getContextPath() + "/craftsman/");
            } catch (SecurityException | IllegalStateException | IOException ex) {
                Logger.getLogger(CraftSpaceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removeInfo() {

    }

    public void editInfo() {
        setEditMode(true);

    }

    public void cancelInfo() {
        setEditMode(false);
    }

    public void saveInfo() {
        craftsmanFacade.edit(getDisplay());
    }

    @ManagedProperty(value = "#{loginManagedBean}")
    private LoginManagedBean lg;

    @EJB
    private ClientQuery clientQuery;

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

    public Craftsman getDisplay() {
        String login = lg.getLogin();
        Client usert = clientQuery.getClientByLogin(login);
        craftsman = (Craftsman) usert;
        return craftsman;
    }

    private UploadedFile getUploadedPicture() {
        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        UploadImgBean uploadBean = (UploadImgBean) elContext.getELResolver().getValue(elContext, null, "uploadBean");
        return uploadBean.getUploadedFile();
    }
}
