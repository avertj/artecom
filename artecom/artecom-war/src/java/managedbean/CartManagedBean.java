 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.entity.Cart;
import model.entity.ProductQuantity;
import model.facade.CartFacade;
import model.facade.ProductFacade;
import model.facade.ProductQuantityFacade;

/**
 *
 * @author Nguyen Bao
 */
@Named(value = "cartManagedBean")
@SessionScoped
public class CartManagedBean implements Serializable {

    @EJB
    private ProductQuantityFacade productQuantityFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CartFacade cartFacade;

    /**
     * Creates a new instance of CartManagedBean
     */
    private Cart cart;

    private ProductQuantity productQuantity;

    private List<Cart> carts;

    private long prodId;

    private int quantity;

    public CartManagedBean() {
        cart = new Cart();
    }

    public CartFacade getCartFacade() {
        return cartFacade;
    }

    public void setCartFacade(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductQuantityFacade getProductQuantityFacade() {
        return productQuantityFacade;
    }

    public void setProductQuantityFacade(ProductQuantityFacade productQuantityFacade) {
        this.productQuantityFacade = productQuantityFacade;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Cart> getCarts() {
        carts = cartFacade.findAll();
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getSize() {
//         if(this.cart.getId() == null){
//            cartFacade.create(cart);
//        }

        int total = 0;
        for (int i = 0; i < this.cart.getList().size(); i++) {
            total += this.cart.getList().get(i).getQuantity();
        }
        cartFacade.edit(cart);
        return ("Cart (" + total + ")");
    }

    public String getPrice() {
//        if(this.cart.getId() == null){
//            cartFacade.create(cart);
//        }
        float sum = 0;
        for (int i = 0; i < this.cart.getList().size(); i++) {
            sum += this.cart.getList().get(i).getPrice();
        }
        cartFacade.edit(cart);
        return ("( " + sum + " )");

    }

    public void removeFromCart() {

        // get position of a product in the list
        int i = this.cart.isInCart(prodId);
        System.out.println("product position " + i);

        //if exist
        if (i != -1) {
            // if the product quantity equal to 1
            if (this.cart.getList().get(i).getQuantity() - 1 == 0) {

                System.out.println(this.cart.getList().get(i).getQuantity() - 1);
                this.cart.removeProduct(i);
                cartFacade.edit(cart);

            } else {
                // if the product quantity is greater than 1
                this.cart.majProd(i, this.cart.getList().get(i).getQuantity() - 1);
                cartFacade.edit(cart);
            }
        } else {
            System.out.println("product doesn't exist!!!");
        }
    }

    public void addToCart() {
        /*if(this.cart.getId() == null){
         cartFacade.create(cart);
         }*/
        int i = this.cart.isInCart(prodId);
        if (i == -1) {
            this.productQuantity = new ProductQuantity();
            this.productQuantity.setProduct(productFacade.find(prodId));
            this.productQuantity.setQuantity(this.quantity);
            this.productQuantityFacade.create(productQuantity);
            this.cart.addtoCart(productQuantity);
        } else {
            this.cart.majProd(i, this.cart.getList().get(i).getQuantity() + this.quantity);
        }
        cartFacade.edit(cart);

    }

}
