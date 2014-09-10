/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author bmf
 */
@Entity
@NamedQuery(name = "", query = "select OBJECT(p) from Product p where p.name like :nom")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Craftsman producer;
    @ManyToOne
    private Craft craft;
    private String name;

    @Lob
    private String description; // sera probablement une chaine html générée par un editeur riche en js

    private Float price;
    // un poids faisant varier le prix des frais de port ou une valeur fixe pour les frais de ports ?
    private Float weight;

    @OneToMany
    private List<Comment> comments;

    private Integer quantity;

    public enum Availability {

        SHIPPABLE,
        NOT_SHIPPABLE
    }
    @Enumerated(EnumType.ORDINAL)
    private Availability availability;

    /**
     * For coccurente edition of Craftsman stock!
     */
    @Version
    private Long version;

    public Product(String name, String description, Float price, Float weight, Integer quantity, Availability availability, Craftsman producer, Craft craft) {
        this.producer = producer;
        this.craft = craft;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.availability = availability;
    }

    public Product() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Craftsman getProducer() {
        return producer;
    }

    public void setProducer(Craftsman producer) {
        this.producer = producer;
    }

    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment e) {
        if (!comments.contains(e)) {
            comments.add(e);
        }
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Float getRating() {
        float sum = 0f;
        for (Comment c : comments) {
            sum += c.getRating();
        }
        return sum / comments.size();
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Product[ id=" + id + " ]";
    }

}
