package managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import model.entity.Address;
import model.facade.AddressFacade;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
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
        /*create("8 Place de la Gare", "Grenoble", 38000);
         create("2 Place Doyen Gosse", "Grenoble", 38000);
         create("4 Rue Félix Poulat", "Grenoble", 38000);
         create("1 Rue Casimir Périer", "Grenoble", 38000);*/
        simpleModel = new DefaultMapModel();

        //Shared coordinates
        LatLng coord1 = new LatLng(36.879466, 30.667648);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(36.879703, 30.706707);
        LatLng coord4 = new LatLng(36.885233, 30.702323);

        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
    }

    /*private void create(String street, String city, Integer postcode) {
     Address addr = new Address();
     addr.setStreet(street);
     addr.setCity(city);
     addr.setPostcode(postcode);
     addressFacade.create(addr);
     }*/
    public void add() {
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
