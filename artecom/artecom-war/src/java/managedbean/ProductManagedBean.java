/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.entity.Client;
import model.entity.Craft;
import model.entity.Craftsman;
import model.entity.Product;
import model.facade.ProductFacade;
import model.queries.ClientQuery;
import model.queries.ProductQueries;

/**
 *
 * @author donatien
 */
@ManagedBean(name="productManagedBean")
@SessionScoped
public class ProductManagedBean {
    
    @ManagedProperty(value="#{loginManagedBean}")
    private LoginManagedBean lg;
    @ManagedProperty(value="#{craftManagedBean}")
    private CraftManagedBean cm;
    private Product produit;
    
    private Long idcraft;

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
        String login = lg.getLogin();
        Client user = clientQuery.getClientByLogin(login);
        craftsman= (Craftsman) user;
        products= productQueries.getProductsById(user.getId());
        return products;
    }

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
        
    }
    
    public void add(){
        
        produit.setCraft(cm.getCraftById(idcraft));
        produit.setCraftsman(craftsman);
        productFacade.create(produit);
        produit= new Product();
    }
    
   
}
