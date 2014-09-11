/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import model.entity.Site;
import model.facade.SiteFacade;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author bmf
 */
public class SiteMapModel extends DefaultMapModel implements Serializable {

    @EJB
    private SiteFacade siteFacade;
    private List<Marker> markers;

    @PostConstruct
    private void init() {
        List<Site> sites = siteFacade.findAll();
        System.out.println(sites.size());
        markers = new ArrayList<>();
        for (Site site : sites) {
            Marker m = new Marker(new LatLng(Double.valueOf(site.getLatlng().getLat()), Double.valueOf(site.getLatlng().getLng())));
            markers.add(m);//new Marker(site.getAddress(), site.getDescription(), null, null));
            addOverlay(m);
        }
    }

    @Override
    public List<Marker> getMarkers() {
        return markers;
    }
}
