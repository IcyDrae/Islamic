package com.IcyDrae;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.IcyDrae.Data.Location;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        Request request = new Request();
        ObjectMapper ObjectMapper = new ObjectMapper();

        String IPAddress = request.Get(
            "http://ip-api.com/json/"
        );

        Location Location = ObjectMapper.readValue(
            IPAddress,
            Location.class
        );

        String PrayerTimes = request.Get(
            "https://api.aladhan.com/v1/timings/now?latitude="
            + Location.getLat()
            + "&longitude="
            + Location.getLon()
            + "&method=3"
        );

        System.out.println("Prayer times: " + PrayerTimes);
    }
}
