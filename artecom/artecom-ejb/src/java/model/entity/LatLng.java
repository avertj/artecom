/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import org.hibernate.search.annotations.Spatial;

/**
 *
 * @author bmf
 */
@Embeddable
@Spatial
@Indexed
public class LatLng implements Serializable {

    @Latitude
    private Double lat;
    @Longitude
    private Double lng;

    public LatLng() {
    }

    public LatLng(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
