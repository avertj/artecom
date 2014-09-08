/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import geocoder.GoogleGeocoder;
import geocoder.GoogleGeocoderResponse;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.entity.Address;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "selectAddressManagedBean")
//@Dependent
@RequestScoped
public class SelectAddressManagedBean {

    //@ManagedProperty(value = "#{addressManagedBean.address}")
    private Address address;
    private GoogleGeocoderResponse geocoderResponse;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GoogleGeocoderResponse getGeocoderResponse() {
        return geocoderResponse;
    }

    public void setGeocoderResponse(GoogleGeocoderResponse geocoderResponse) {
        this.geocoderResponse = geocoderResponse;
    }

    public void lookupAddress() {
        try {
            /*address = new Address();
             address.setStreet("1 rue colonel dumont");
             address.setCity("Grenoble");*/
            setGeocoderResponse(GoogleGeocoder.getGeocoderResponse(address));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(geocoderResponse.getStatus()));
            //if (geo.status.equals("OK")) { // Requete OK
            //if (geo.results.length > 1) { // Plusieures adresses retournées
            //RequestContext.getCurrentInstance().openDialog("selectAddress");
            //addressFacade.create(address);
            //address = new Address();
            //} else { // une seule adresse trouvée
            // confirmation
            //}
            //}
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur Geocoder API", "Une erreur est survenue lors de l'accès a l'API geocder de Google.\n" + ex.getMessage()));
        }
    }

}
