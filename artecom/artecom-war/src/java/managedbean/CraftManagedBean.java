/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import model.entity.Craft;
import model.facade.CraftFacade;
import model.queries.CraftQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name="craftManagedBean")
@SessionScoped
public class CraftManagedBean {
    @EJB
    private CraftFacade craftFacade;
    
    private Craft craft;
    
    private String idparent;
    
    private List<Craft> crafts ;
    
    private CraftQueries craftQueries;

    public String getIdparent() {
        return idparent;
    }

    public void setIdparent(String idparent) {
        this.idparent = idparent;
    }

    public CraftFacade getCraftFacade() {
        return craftFacade;
    }
    public CraftManagedBean(){
        craft= new Craft();
        
        
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
    
    public List<Craft> getCrafts(){
         
        return crafts;
    }
    public String add(){
        for(Craft c: crafts){
            if(c.getName().equals(idparent)){
               craft.setParent(c);
            }
        }      
        craftFacade.create(craft);
        return "craft?faces-redirect=true";
    }
    
    public ArrayList getChoices(){
        ArrayList lis = new ArrayList();
        crafts=craftFacade.findAll();
        for(Craft c: crafts){
            lis.add(c.getName());
        }
        return lis;
    }
}
