/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoder;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import model.entity.Address;

/**
 *
 * @author bmf
 */
public class GoogleGeocoder {

    private static final String GOOGLE_GEOCODER_URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=";
    private static final Gson gson = new Gson();

    public static GoogleGeocoderResponse getGeocoderResponse(Address address) throws IOException {
        URL url = new URL(GOOGLE_GEOCODER_URL + URLEncoder.encode(address.getStreet() + ", " + address.getCity()));
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        String jsonResult = "";
        while ((inputLine = in.readLine()) != null) {
            jsonResult += inputLine;
        }
        in.close();
        return gson.fromJson(jsonResult, GoogleGeocoderResponse.class);
    }
}
