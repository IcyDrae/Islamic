package com.IcyDrae.Services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import com.IcyDrae.Data.Location;
import com.IcyDrae.Data.NextPrayer;
import com.IcyDrae.Data.PrayerTimesResponse;
import com.IcyDrae.Data.Timings;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrayerTimesService {
    RequestService Request;
    LocationService LocationService;
    Location Location;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public PrayerTimesService() throws Exception {
        this.Request = new RequestService();
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

    public NextPrayer fetchNextPrayerTime(Timings Timings) throws Exception {
        Map<String, String> Prayers = new LinkedHashMap<>();

        Prayers.put("Fajr", Timings.Fajr);
        Prayers.put("Dhuhr", Timings.Dhuhr);
        Prayers.put("Asr", Timings.Asr);
        Prayers.put("Maghrib", Timings.Maghrib);
        Prayers.put("Isha", Timings.Isha);

        LocalTime Now = LocalTime.now();

        for (Map.Entry<String, String> Prayer : Prayers.entrySet()) {
            LocalTime PrayerTime =
                    LocalTime.parse(Prayer.getValue(), FORMATTER);

            if (PrayerTime.isAfter(Now)) {
                return new NextPrayer(Prayer.getKey(), PrayerTime);
            }
        }

        // All prayers have passed, next prayer is tomorrow's Fajr
        return new NextPrayer("Fajr", LocalTime.parse(Timings.Fajr, FORMATTER));
    }
}
