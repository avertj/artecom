/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoder;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bmf
 */
public class GoogleGeocoderResponse {

    public String status;
    public results[] results;

    public GoogleGeocoderResponse() {
    }

    public class results {

        public String formatted_address;
        public geometry geometry;
        public String[] types;
        public address_component[] address_components;

        public String getFormatted_address() {
            return formatted_address;
        }

        public String getFormattedAddress() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(geometry geometry) {
            this.geometry = geometry;
        }

        public String[] getTypes() {
            return types;
        }

        public void setTypes(String[] types) {
            this.types = types;
        }

        public address_component[] getAddress_components() {
            return address_components;
        }

        public void setAddress_components(address_component[] address_components) {
            this.address_components = address_components;
        }

    }

    public class geometry {

        public bounds bounds;
        public String location_type;
        public location location;
        public bounds viewport;

        public bounds getBounds() {
            return bounds;
        }

        public void setBounds(bounds bounds) {
            this.bounds = bounds;
        }

        public String getLocation_type() {
            return location_type;
        }

        public void setLocation_type(String location_type) {
            this.location_type = location_type;
        }

        public location getLocation() {
            return location;
        }

        public void setLocation(location location) {
            this.location = location;
        }

        public bounds getViewport() {
            return viewport;
        }

        public void setViewport(bounds viewport) {
            this.viewport = viewport;
        }

    }

    public class bounds {

        public location northeast;
        public location southwest;

        public location getNortheast() {
            return northeast;
        }

        public void setNortheast(location northeast) {
            this.northeast = northeast;
        }

        public location getSouthwest() {
            return southwest;
        }

        public void setSouthwest(location southwest) {
            this.southwest = southwest;
        }

    }

    public class location {

        public String lat;
        public String lng;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

    }

    public class address_component {

        public String long_name;
        public String short_name;
        public String[] types;

        public String getLong_name() {
            return long_name;
        }

        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String[] getTypes() {
            return types;
        }

        public void setTypes(String[] types) {
            this.types = types;
        }

    }

    public List<results> getResults() {
        return Arrays.asList(results);
    }

    public String getStatus() {
        return status;
    }

}
