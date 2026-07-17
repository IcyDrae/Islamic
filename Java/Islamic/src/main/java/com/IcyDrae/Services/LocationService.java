package com.IcyDrae.Services;

import com.IcyDrae.Request;
import com.IcyDrae.Data.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationService {
    Request Request;

    public LocationService() {
        this.Request = new Request();
    }

    public Location fetchLocation() throws Exception {
        ObjectMapper ObjectMapper = new ObjectMapper();

        String LocationResponse = this.Request.Get(
            "http://ip-api.com/json/"
        );

        Location LocationData = ObjectMapper.readValue(
            LocationResponse,
            Location.class
        );

        return LocationData;
    }
}
