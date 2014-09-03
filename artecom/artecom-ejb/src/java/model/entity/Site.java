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
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Address address;
    @ManyToMany
    private List<Craftsmanship> craftsmanships;
    @OneToMany
    private List<Product> products;
    
    // une enum pour les types de site
    // possibilité de virer l'enum pour qq chose de plus "GL"
    public enum Type {
        STORE,
        WORKSHOP
    }
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    
    public List<Craftsmanship> getCraftsmanships() {
        return craftsmanships;
    }

    public void setCraftsmanships(List<Craftsmanship> craftsmanships) {
        this.craftsmanships = craftsmanships;
    }

    // contains utilise la fonction equals de Craftsmaship donc je ne sais pas trop si ça fonctionne avec la surcharge générée automatiquement
    public List<Craftsmanship> addCraftsmanship(Craftsmanship craftmanship) {
        if (!craftsmanships.contains(craftmanship)) {
            craftsmanships.add(craftmanship);
        }
        return craftsmanships; // permet de faire des choses genre list.add(craft1).add(craft2).add(craft3) 
    }

    // idem qu'au dessus
    public List<Craftsmanship> removeCraftsmanship(Craftsmanship craftmanship) {
        if (craftsmanships.contains(craftmanship)) {
            craftsmanships.remove(craftmanship);
        }
        return craftsmanships; // permet de faire des choses genre list.add(craft1).add(craft2).add(craft3) 
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.site[ id=" + id + " ]";
    }

}
