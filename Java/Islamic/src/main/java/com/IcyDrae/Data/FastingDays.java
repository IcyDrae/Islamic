package com.IcyDrae.Data;

import java.util.List;

public class FastingDays {
    private boolean mondayThursday;
    private boolean whiteDays;
    private boolean ramadan;
    private int ramadanDay;
    private int daysUntilRamadan;
    private List<String> voluntaryRecommendations;

    public boolean isMondayThursday() {
        return mondayThursday;
    }

    public void setMondayThursday(boolean mondayThursday) {
        this.mondayThursday = mondayThursday;
    }

    public boolean isWhiteDays() {
        return whiteDays;
    }

    public void setWhiteDays(boolean whiteDays) {
        this.whiteDays = whiteDays;
    }

    public boolean isRamadan() {
        return ramadan;
    }

    public void setRamadan(boolean ramadan) {
        this.ramadan = ramadan;
    }

    public int getRamadanDay() {
        return ramadanDay;
    }

    public void setRamadanDay(int ramadanDay) {
        this.ramadanDay = ramadanDay;
    }

    public int getDaysUntilRamadan() {
        return daysUntilRamadan;
    }

    public void setDaysUntilRamadan(int daysUntilRamadan) {
        this.daysUntilRamadan = daysUntilRamadan;
    }

    public List<String> getVoluntaryRecommendations() {
        return voluntaryRecommendations;
    }

    public void setVoluntaryRecommendations(List<String> voluntaryRecommendations) {
        this.voluntaryRecommendations = voluntaryRecommendations;
    }
}
