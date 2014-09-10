/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    @ManyToMany(mappedBy = "sites")
    private List<Craft> crafts;
    @OneToMany
    private List<Product> products;

    @ManyToOne
    private Craftsman craftsman;

    @Embedded
    private LatLng latlng;

    // une enum pour les types de site
    // possibilité de virer l'enum pour qq chose de plus "GL"
    public enum Type {

        STORE,
        WORKSHOP,
        ONMARKET
    }
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Lob
    private String description;
    @Lob
    private String opening;

    public Site(Address address, List<Craft> crafts, Craftsman craftsman, Type type, String description) {
        this.address = address;
        this.crafts = crafts;
        this.craftsman = craftsman;
        this.type = type;
        this.description = description;
    }

    public Site() {
    }

    public List<Craft> getCrafts() {
        return crafts;
    }

    public void setCrafts(List<Craft> crafts) {
        this.crafts = crafts;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public List<Craft> getCraftsmanships() {
        return crafts;
    }

    public void setCraftsmanships(List<Craft> craftsmanships) {
        this.crafts = craftsmanships;
    }

    // contains utilise la fonction equals de Craftsmaship donc je ne sais pas trop si ça fonctionne avec la surcharge générée automatiquement
    public List<Craft> addCraftsmanship(Craft craftmanship) {
        if (!crafts.contains(craftmanship)) {
            crafts.add(craftmanship);
        }
        return crafts; // permet de faire des choses genre list.add(craft1).add(craft2).add(craft3)
    }

    // idem qu'au dessus
    public List<Craft> removeCraftsmanship(Craft craftmanship) {
        if (crafts.contains(craftmanship)) {
            crafts.remove(craftmanship);
        }
        return crafts; // permet de faire des choses genre list.add(craft1).add(craft2).add(craft3)
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

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
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
