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
    private long lat;
    private long lon;
    private long dist;

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public long getDist() {
        return dist;
    }

    public void setDist(long dist) {
        this.dist = dist;
    }
    
}
