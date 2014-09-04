/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author inilog
 */
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<ProductQuantity> products;

    public List<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantity> products) {
        this.products = products;
    }

    public void removeProduct(Product p) {
        if (products.contains(p)) {
            products.remove(products.get(products.indexOf(p)));
        }
    }

    public void updateQuantity(Product p, int quantity) {
        if (products.contains(p)) {
            ProductQuantity pq = products.get(products.indexOf(p));
            pq.setQuantity(quantity);
        }
    }

    public void addProduct(Product p, int quantity) {
        if (!products.contains(p)) {
            ProductQuantity pq = new ProductQuantity();
            pq.setProduct(p);
            pq.setQuantity(quantity);
        }
    }

    public float getPrice() {
        float sum = 0;
        for (ProductQuantity pq : products) {
            sum += pq.getPrice();
        }
        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Carte[ id=" + id + " ]";
    }

}
