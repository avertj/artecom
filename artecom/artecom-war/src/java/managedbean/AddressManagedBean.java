package managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.entity.Address;
import model.facade.AddressFacade;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "addressManagedBean")
public class AddressManagedBean {

    @EJB
    private AddressFacade addressFacade;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public List<Address> getAddresses() {
        return addressFacade.findAll();
    }

    public void add() {
        addressFacade.create(address);
        address = new Address();
    }

    /**
     * Creates a new instance of AdressManagedBean
     */
    public AddressManagedBean() {
        address = new Address();
    }

}
