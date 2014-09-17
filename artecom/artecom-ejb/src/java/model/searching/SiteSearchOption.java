/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.searching;

/**
 *
 * @author inilog
 */
public class SiteSearchOption {
    private Long lat;
    private Long lon;
    private Long dist;

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getDist() {
        return dist;
    }

    public void setDist(Long dist) {
        this.dist = dist;
    }
    
}
