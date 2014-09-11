/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entity.Site;
import model.queries.SiteQueries;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author bmf
 */
@Named(value = "mapView")
@ViewScoped
public class MapView implements Serializable {

    @EJB
    private SiteQueries siteQueries;

    private MapModel mapModel;

    /**
     * Creates a new instance of MapView
     */
    public MapView() {
    }

    @PostConstruct
    private void init() {
        mapModel = new DefaultMapModel();
        List<Site> sites = siteQueries.getSites();
        for (Site site : sites) {
            Marker m = new Marker(new LatLng(Double.valueOf(site.getLatlng().getLat()), Double.valueOf(site.getLatlng().getLng())), site.getAddress().getName());
            mapModel.addOverlay(m);
        }
    }

    public MapModel getMapModel() {
        return mapModel;
    }

}
