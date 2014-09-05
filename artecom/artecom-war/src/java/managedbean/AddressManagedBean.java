package managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.entity.Address;
import model.facade.AddressFacade;
import org.primefaces.context.RequestContext;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "addressManagedBean")
@RequestScoped
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

    public void openSelectDialog() {
        RequestContext.getCurrentInstance().openDialog("sectAddress");
    }

    /**
     * Creates a new instance of AdressManagedBean
     */
    public AddressManagedBean() {
        address = new Address();
    }

}
