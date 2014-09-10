/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.entity.Craft;
import model.facade.CraftFacade;
import model.queries.CraftQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "craftManagedBean")
@SessionScoped
public class CraftManagedBean {

    @EJB
    private CraftFacade craftFacade;

    private Craft craft;

    private Long idparent;

    private List<Craft> crafts;

    private CraftQueries craftQueries;

    public Long getIdparent() {
        return idparent;
    }

    public void setIdparent(Long idparent) {
        this.idparent = idparent;
    }

    public CraftFacade getCraftFacade() {
        return craftFacade;
    }

    public CraftManagedBean() {
        craft = new Craft();

    }

    public void setCraftFacade(CraftFacade craftFacade) {
        this.craftFacade = craftFacade;
    }

    public Craft getCraft() {

        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    public List<Craft> getCrafts() {
        crafts = craftFacade.findAll();
        return crafts;
    }

    public String add() {
        for (Craft c : crafts) {
            if (c.getId().equals(idparent)) {
                craft.setParent(c);
            }
        }
        craftFacade.create(craft);
        //craft = new Craft();
        return "craft?faces-redirect=true";
    }
}
