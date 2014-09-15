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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import map.SiteMarker;
import model.entity.Site;
import model.queries.SiteQueries;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

/**
 *
 * @author bmf
 */
@ManagedBean(name = "mapView")
@ViewScoped
public class MapView implements Serializable {

    @EJB
    private SiteQueries siteQueries;

    private MapModel mapModel;
    private SiteMarker marker;

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
            System.out.println(site.getDescription());
            System.out.println(site.getCraftsman().getFirstName());
            SiteMarker m = new SiteMarker(new LatLng(Double.valueOf(site.getLatlng().getLat()), Double.valueOf(site.getLatlng().getLng())), site.getAddress().getName(), site);
            mapModel.addOverlay(m);
        }
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (SiteMarker) event.getOverlay();
    }

    public SiteMarker getMarker() {
        return marker;
    }

}
