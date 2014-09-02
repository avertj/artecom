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
import javax.persistence.OneToMany;

/**
 *
 * @author inilog
 */
@Entity
@DiscriminatorValue("C")
public class Client extends Profil implements Serializable {
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
    
}
