package managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import geocoder.GoogleGeocoder;
import geocoder.GoogleGeocoderResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import model.entity.Address;
import model.facade.AddressFacade;
import org.primefaces.model.map.MapModel;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "addressManagedBean")
@Stateless
public class AddressManagedBean {

    @EJB
    private AddressFacade addressFacade;

    private Address address;
    private List<Address> addresses;

    //private Address address;
    @PostConstruct
    public void init() {
    }

    /*private void create(String street, String city, Integer postcode) {
     Address addr = new Address();
     addr.setStreet(street);
     addr.setCity(city);
     addr.setPostcode(postcode);
     addressFacade.create(addr);
     }*/
    public void add() {
        try {
            GoogleGeocoderResponse resp = GoogleGeocoder.getGeocoderResponse(address);
            if (resp.results.length > 0 && resp.results[0].types[0].equalsIgnoreCase("street_address")) {
                address.setStreet(resp.results[0].address_components[0].long_name + " " + resp.results[0].address_components[1].long_name);
                address.setCity(resp.results[0].address_components[2].long_name);
                address.setPostcode(Integer.valueOf(resp.results[0].address_components[6].long_name));
            }
        } catch (IOException ex) {
            Logger.getLogger(AddressManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        addressFacade.create(address);
        address = new Address();
        //craft = new Craft();
        //return "address?faces-redirect=true";
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddresses() {
        addresses = (addresses == null ? addressFacade.findAll() : addresses);
        return addresses;
    }

    /*public void openSelectDialog() {
     RequestContext.getCurrentInstance().openDialog("sectAddress");
     }*/
    /**
     * Creates a new instance of AdressManagedBean
     */
    public AddressManagedBean() {
        address = new Address();
    }

    /* TEST */
    private MapModel simpleModel;

    public MapModel getSimpleModel() {
        return simpleModel;
    }

}
