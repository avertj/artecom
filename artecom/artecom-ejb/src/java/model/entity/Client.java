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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PROFIL_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("C")
public class Client implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany
    private List<Address> address;
    private Long lastBillingAdress;
    private Long lastShippingAdress;

    public Long getLastBillingAdress() {
        return lastBillingAdress;
    }

    public void setLastBillingAdress(Long lastBillingAdress) {
        this.lastBillingAdress = lastBillingAdress;
    }

    public Long getLastShippingAdress() {
        return lastShippingAdress;
    }

    public void setLastShippingAdress(Long lastShippingAdress) {
        this.lastShippingAdress = lastShippingAdress;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Profil[ id=" + id + " ]";
    }

}
