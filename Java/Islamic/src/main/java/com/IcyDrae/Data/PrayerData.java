package com.IcyDrae.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrayerData {

    private Timings timings;

    public Timings getTimings() {
        return timings;
    }
}
