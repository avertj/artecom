/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author bmf
 */
@Entity
@DiscriminatorValue("P")
@Indexed
public class Craftsman extends Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "craftsman")
    private List<Product> products;

    @OneToMany(mappedBy = "craftsman")
    private List<Site> sites;

    @Lob
    @Column(length=20971520)
    @Analyzer(definition = "fr.full")
    @Field
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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
