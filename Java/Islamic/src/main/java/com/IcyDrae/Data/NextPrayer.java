package com.IcyDrae.Data;

import java.time.LocalTime;

public class NextPrayer {
    private String Name;
    private LocalTime Time;

    public NextPrayer(String Name, LocalTime Time) {
        this.Name = Name;
        this.Time = Time;
    }

    public String getName() {
        return this.Name;
    }

    public LocalTime getTime() {
        return this.Time;
    }
}
