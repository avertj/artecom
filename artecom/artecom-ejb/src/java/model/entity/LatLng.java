/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.Embeddable;

/**
 *
 * @author bmf
 */
@Embeddable
public class LatLng {

    private String lat;
    private String lng;

    public LatLng() {
    }

    public LatLng(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

}
