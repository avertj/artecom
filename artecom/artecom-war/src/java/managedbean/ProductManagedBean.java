/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.entity.Client;
import model.entity.Craftsman;
import model.entity.Product;
import model.entity.Product.Availability;
import model.entity.Site;
import model.facade.ProductFacade;
import model.queries.ClientQuery;
import model.queries.ProductQueries;
import model.queries.SiteQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name="productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable{
    
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg;
    
    private Boolean editMode;
    
    private int idAvailability;

    public int getIdAvailability() {
        return idAvailability;
    }

    public void setIdAvailability(int idAvailability) {
        this.idAvailability = idAvailability;
    }
    
    @ManagedProperty(value="#{craftManagedBean}")
    private CraftManagedBean cm;
    
    @ManagedProperty(value="#{siteManagedBean}")
    private SiteManagedBean sm;
    
    private SiteQueries siteQueries;
    
    
    private Product produit;
    
    private Product editProd=null;
    
    
    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public SiteQueries getSiteQueries() {
        siteQueries=sm.getSiteQueries();
        return siteQueries;
    }

    public void setSiteQueries(SiteQueries siteQueries) {
        this.siteQueries = siteQueries;
    }

    public SiteManagedBean getSm() {
        return sm;
    }

    public void setSm(SiteManagedBean sm) {
        this.sm = sm;
    }

    public CraftManagedBean getCm() {
       
        return cm;
    }

    public void setCm(CraftManagedBean cm) {
        this.cm = cm;
    }
    
    public Product getEditProd() {
        return editProd;
    }

    public void setEditProd(Product editProd) {
        this.editProd = editProd;
    }
    
    private Long idcraft;
    
    private Long idsite;
    
    private Long idcraftl;

    public Long getIdcraftl() {
        return idcraftl;
    }

    public void setIdcraftl(Long idcraftl) {
        this.idcraftl = idcraftl;
    }

    public Long getIdsitel() {
        return idsitel;
    }

    public void setIdsitel(Long idsitel) {
        this.idsitel = idsitel;
    }
    
    private Long idsitel;

    public Long getIdsite() {
        return idsite;
    }

    public void setIdsite(Long idsite) {
        this.idsite = idsite;
    }

    public Long getIdcraft() {
        return idcraft;
    }

    public void setIdcraft(Long idcraft) {
        this.idcraft = idcraft;
    }
    
    private Craftsman craftsman;

    public Craftsman getCraftsman() {
         return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }
    
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

    public Product getProduit() {
        return produit;
    }

    public void setProduit(Product produit) {
        this.produit = produit;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public ProductQueries getProductQueries() {
        return productQueries;
    }

    public void setProductQueries(ProductQueries productQueries) {
        this.productQueries = productQueries;
    }

   public List<Product> getProducts() {
        products=productFacade.findAll();
        return products;
    }
   public List<Product> getCraftsmanProducts() {
        String login = lg.getLogin();
        Client user = clientQuery.getClientByLogin(login);
        craftsman= (Craftsman) user;
        products= productQueries.getProductsById(user.getId());
        return products;
    }
     /*public Hashtable<Boolean,Product> getProducts() {
         Hashtable tableProd = new Hashtable();
         String login = lg.getLogin();
        Client user = clientQuery.getClientByLogin(login);
        craftsman= (Craftsman) user;
        products= productQueries.getProductsById(user.getId());
        for(Product p:products){
            tableProd.put(editMode,p);
        }
        return tableProd;
    }*/
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    @EJB
    private ProductFacade productFacade;
    
    @EJB
    private ProductQueries productQueries;
    
    private List<Product> products;
    
    public ProductManagedBean(){
        produit= new Product();
        editMode=false;
        //editProd=new Product();
        
    }
    public Site getSite(Long id){
        List<Site> list = sm.getSites();
        Site site=null;
        for(Site s:list){
            if(s.getId().equals(id)){
                site=s;
            }
        }
        return site;
    }
    public void add(){
        produit.setEditable(Boolean.FALSE);
        produit.setSite(getSite(idsite));
        produit.setAvailability(getAvailability(idAvailability));
        produit.setCraft(cm.getCraftById(idcraft));
        produit.setCraftsman(craftsman);
        productFacade.create(produit);
        setEditMode(false);
        produit= new Product();
        idcraft=null;
        idsite=null;
        
    }
    
      
    public void removeProd(Product p){
        productFacade.remove(p);
        
    }
    
    public void editProd(Product p){
        
        //setEditMode(Boolean.TRUE);
        editProd=p;
        p.setEditable(Boolean.TRUE);
        productFacade.edit(p);
        
    }
    public void cancelProd(Product p){
        p.setEditable(Boolean.FALSE);
        productFacade.edit(p);
    }
    
    public void saveProd(Product p){
       
        //editProd.setSite(getSite(idcraftl));
        //editProd.setCraft(cm.getCraftById(idcraftl));
        //p.setAvailability(editProd.getAvailability());
        //productFacade.find(p.getId());
        p.setEditable(Boolean.FALSE);
        productFacade.edit(p);
        
    }
    
    public void ajoutMode(){
        setEditMode(true);
    }
     public String cancelMode(){
        setEditMode(false);
        return "/craftsman/gestprod.jsf";
    }
     
     public Availability getAvailability(int id){
        Availability type=null;
        switch(id){
            case 0 : 
                return null;
            case 1 :
                type=Availability.NOT_SHIPPABLE;
                break;
            case 2 :
                type=Availability.SHIPPABLE;
                break;            
        }
        return type;
    }
    
   
}
