/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.io.Serializable;
import model.entity.Site;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author bmf
 */
public class SiteMarker extends Marker implements Serializable {

    private Site site;

    public SiteMarker(LatLng latlng) {
        super(latlng);
    }

    public SiteMarker(LatLng latlng, String title) {
        super(latlng, title);
    }

    public SiteMarker(LatLng latlng, String title, Site data) {
        super(latlng, title, null);
        site = data;
    }

    public SiteMarker(LatLng latlng, String title, Site data, String icon) {
        super(latlng, title, null, icon);
        site = data;
    }

    public SiteMarker(LatLng latlng, String title, Site data, String icon, String shadow) {
        super(latlng, title, null, icon, shadow);
        site = data;
    }

    public Site getSite() {
        return site;
    }

}
