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
 * @author bmf
 */
@Entity
@DiscriminatorValue("P")
public class Producer extends Profil implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<ProductQuantity> products;
    @OneToMany
    private List<Site> sites;
    
    private String name;
    @ManyToOne
    private Address address;

    public List<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantity> products) {
        this.products = products;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
