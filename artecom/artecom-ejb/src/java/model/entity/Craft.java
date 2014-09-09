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
public class Craft implements Serializable {
    public Craft(){
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
     private List<Site> site;
    
    @ManyToOne
    private Craft parent;

    public Craft getParent() {
        return parent;
    }

    public void setParent(Craft parent) {
        this.parent = parent;
    }

    public List<Site> getSite() {
     return site;
     }

     public void setSite(List<Site> site) {
     this.site = site;
     }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

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
        if (!(object instanceof Craft)) {
            return false;
        }
        Craft other = (Craft) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Craftsmanship[ id=" + id + " ]";
    }

}
