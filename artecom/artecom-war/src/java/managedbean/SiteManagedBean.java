/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.entity.Address;
import model.entity.Client;
import model.entity.Craft;
import model.entity.Craftsman;
import model.entity.Site;
import model.facade.AddressFacade;
import model.facade.SiteFacade;
import model.queries.AddressQuery;
import model.queries.ClientQuery;
import model.queries.CraftQueries;
import model.queries.SaleQuery;
import model.queries.SiteQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name="siteManagedBean")
@SessionScoped
public class SiteManagedBean implements Serializable{
    
     
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg; 
    
    
    @ManagedProperty(value="#{craftManagedBean}")
    private CraftManagedBean cm;
    
    private Craftsman craftsman;
    
    @EJB
    private SiteFacade siteFacade;
    
    @EJB
    private CraftQueries craftQueries;
    
    @EJB
    private AddressQuery addressQuery;
    
    @EJB
    private ClientQuery clientQuery;
    
    @EJB
    private AddressFacade addressFacade;
    
    @EJB
    private SiteQueries siteQueries;
    
    private Address address;
    
    private ArrayList<Craft> siteCrafts;
    
    private List<Site> sites;

    private Site site;
    
    private Long craftId;
     
    private Craft craft;
    
    public Craftsman getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }

    public CraftManagedBean getCm() {
        return cm;
    }

    public void setCm(CraftManagedBean cm) {
        this.cm = cm;
    }

    public LoginManagedBean getLg() {
        return lg;
    }

    public void setLg(LoginManagedBean lg) {
        this.lg = lg;
    }

    public CraftQueries getCraftQueries() {
        return craftQueries;
    }

    public void setCraftQueries(CraftQueries craftQueries) {
        this.craftQueries = craftQueries;
    }

    public AddressQuery getAddressQuery() {
        return addressQuery;
    }

    public void setAddressQuery(AddressQuery addressQuery) {
        this.addressQuery = addressQuery;
    }

    public ClientQuery getClientQuery() {
        return clientQuery;
    }

    public void setClientQuery(ClientQuery clientQuery) {
        this.clientQuery = clientQuery;
    }

    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    public void setAddressFacade(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    public SiteQueries getSiteQueries() {
        return siteQueries;
    }

    public void setSiteQueries(SiteQueries siteQueries) {
        this.siteQueries = siteQueries;
    }
    
    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
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
        String login = lg.getLogin();
        Client user = clientQuery.getClientByLogin(login);
        craftsman= (Craftsman) user;
        sites = siteQueries.getSites(craftsman);
        return sites;
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
    
    
    @RolesAllowed({"craftsman"})
    public void addCraft(){
        
        craft = craftQueries.getCraft(craft.getId());
        siteCrafts.add(craft);
        craft= new Craft();      
    }
    
    @RolesAllowed({"craftsman"})
    public void removeCraft(Craft c){
        
        for(int i=0;i<siteCrafts.size();i++){
            if (siteCrafts.get(i).equals(c)){
                siteCrafts.remove(i);
            }
        }
            
    }
    
    public void addSite(){
        addressFacade.create(address);
        Address adr = addressQuery.getAddressByName(address.getName());
        site.setAddress(adr);
        site.setCraftsman(craftsman);
        site.setCraftsmanships(siteCrafts); 
        siteFacade.create(site);
        site = new Site();
        address= new Address();
        siteCrafts= new ArrayList();
        
    }
}