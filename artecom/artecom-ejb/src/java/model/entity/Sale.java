/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author inilog
 */
@Entity
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Cart cart; // A VOIR !! pourquoi ne pas mettre la list ProductQuantity ici ?
    private float price;
    @ManyToOne
    private Address billingAdress;
    @ManyToOne
    private Address shippingAdress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    // peut etre utile pour les frais de ports
    /*public enum ShippingMethod {

     POSTAL,
     ON_SITE
     }
     @Enumerated(EnumType.ORDINAL)
     private ShippingMethod shippingMethod;*/

    // êtats possible pour une commande
    public enum Status {

        WAITING_FOR_PAYMENT, // pour les chèques. les commandes dans cet êtat ne sont visibles que par les admins
        WAITING_FOR_SHIPMENT, // paiement effectué, commande visible par l'artisan
        SENT, // utile uniquement pour l'envoi postal
        DELIVERED // on passe directement à ça dans le cas d'une vente sur place (si on garde ce système)
    }
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Sale(Cart cart, float price, Address billingAdress, Address shippingAdress, Date createdOn, Status status) {
        this.cart = cart;
        this.price = price;
        this.billingAdress = billingAdress;
        this.shippingAdress = shippingAdress;
        this.createdOn = createdOn;
        this.status = status;
    }

    public Sale() {
    }

    public Address getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(Address billingAdress) {
        this.billingAdress = billingAdress;
    }

    public Address getShippingAdress() {
        return shippingAdress;
    }

    public void setShippingAdress(Address shippingAdress) {
        this.shippingAdress = shippingAdress;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /*public ShippingMethod getShippingMethod() {
     return shippingMethod;
     }

     public void setShippingMethod(ShippingMethod shippingMethod) {
     this.shippingMethod = shippingMethod;
     }*/
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Sale[ id=" + id + " ]";
    }

}
