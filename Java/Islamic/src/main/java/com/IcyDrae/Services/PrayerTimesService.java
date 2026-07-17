package com.IcyDrae.Services;

import com.IcyDrae.Request;
import com.IcyDrae.Data.Location;
import com.IcyDrae.Data.PrayerTimesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrayerTimesService {
    Request Request;
    LocationService LocationService;
    Location Location;

    public PrayerTimesService() throws Exception {
        this.Request = new Request();
        this.LocationService = new LocationService();
        this.Location = this.LocationService.fetchLocation();
    }

    public PrayerTimesResponse fetchAllPrayerTimesForToday() throws Exception {
        ObjectMapper ObjectMapper = new ObjectMapper();

        String URL = "https://api.aladhan.com/v1/timings/now?latitude="
                    + this.Location.getLat()
                    + "&longitude="
                    + this.Location.getLon()
                    + "&method=3";

        String PrayerTimesResponse = this.Request.Get(URL);

        PrayerTimesResponse Response = ObjectMapper.readValue(
                PrayerTimesResponse,
                PrayerTimesResponse.class
            );

        return Response;
    }
}
