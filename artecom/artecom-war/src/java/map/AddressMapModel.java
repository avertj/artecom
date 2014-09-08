/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import model.entity.Site;
import model.facade.SiteFacade;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Overlay;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import org.primefaces.model.map.Rectangle;

/**
 *
 * @author bmf
 */
public class AddressMapModel implements MapModel {

    @EJB
    private SiteFacade siteFacade;

    @Override
    public void addOverlay(Overlay ovrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marker> getMarkers() {
        List<Site> sites = siteFacade.findAll();
        List<Marker> markers = new ArrayList<>();
        for (Site site : sites) {
            markers.add(null);//new Marker(site.getAddress(), site.getDescription(), null, null));
        }
        return markers;
    }

    @Override
    public List<Polyline> getPolylines() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Polygon> getPolygons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Circle> getCircles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rectangle> getRectangles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Overlay findOverlay(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
