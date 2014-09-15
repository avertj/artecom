/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.entity.Address;
import model.entity.Craft;
import model.entity.Site;
import model.facade.AddressFacade;
import model.facade.SiteFacade;
import model.queries.AddressQuery;
import model.queries.CraftQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name="siteManagedBean")
@ApplicationScoped
public class SiteManagedBean {
    
    @EJB
    private SiteFacade siteFacade;
    
    @EJB
    private CraftQueries craftQueries;
    
    @EJB
    private AddressQuery addressQuery;
    
    @EJB
    private AddressFacade addressFacade;
    
    private Address address;
    
    private ArrayList<Craft> siteCrafts;
    
    private List<Site> sites;

    private Site site;
    
    private Long craftId;
     
    private Craft craft;

    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }
    
    private ArrayList list;

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
    
    public SiteManagedBean(){
        site=new Site();
        address= new Address(); 
        siteCrafts= new ArrayList();
        craft = new Craft();
        
    }
   
    public ArrayList<Craft> getSiteCrafts() {
        return siteCrafts;
    }

    public void setSiteCrafts(ArrayList<Craft> siteCrafts) {
        this.siteCrafts = siteCrafts;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Long getCraftId() {
        return craftId;
    }

    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }
    
    public List<Site> getSites() {
        return siteFacade.findAll();
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
    
    public SiteFacade getSiteFacade() {
        return siteFacade;
    }

    public void setSiteFacade(SiteFacade siteFacade) {
        this.siteFacade = siteFacade;
    }
    
    
    public void addCraft(){
        
        craft = craftQueries.getCraft(craft.getId());
        siteCrafts.add(craft);
        craft= new Craft();      
    }
    
    public void addSite(){
        addressFacade.create(address);
        Address adr = addressQuery.getAddressByName(address.getName());
        site.setAddress(adr);     
        site.setCraftsmanships(siteCrafts);
        siteFacade.create(site);
        site = new Site();
        address= new Address();
        siteCrafts= new ArrayList();
        
    }
}