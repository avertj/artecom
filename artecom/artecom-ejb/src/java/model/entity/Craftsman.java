/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 *
 * @author bmf
 */
@Entity
@DiscriminatorValue("P")
public class Craftsman extends Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany
    private List<ProductQuantity> products;

    @OneToMany
    private List<Site> sites;

    @Lob
    private String description;

    @OneToMany
    private List<Comment> comments;

    public Craftsman(String firstName, String lastName, String description) {
        super(firstName, lastName);
        this.description = description;
    }

    public Craftsman() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Float getRating() {
        float sum = 0f;
        for (Comment c : comments) {
            sum += c.getRating();
        }
        return sum / comments.size();
    }
}
