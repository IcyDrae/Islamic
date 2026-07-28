package com.IcyDrae.Data;

public class HijriCalendar {
    private int Day;
    private int Week;
    private String DayOfWeek;
    private int Month;
    private String MonthName;
    private int Year;
    private boolean isRamadan;
    private int dayOfRamadan;
    private int daysUntilRamadan;

    public int getDay() {
        return this.Day;
    }

    public void setDay(int Day) {
        this.Day = Day;
    }

    public int getWeek() {
        return this.Week;
    }

    public void setWeek(int Week) {
        this.Week = Week;
    }

    public String getDayOfWeek() {
        return this.DayOfWeek;
    }

    public void setDayOfWeek(String DayOfWeek) {
        this.DayOfWeek = DayOfWeek;
    }

    public int getMonth() {
        return this.Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public String getMonthName() {
        return this.MonthName;
    }

    public void setMonthName(String monthName) {
        this.MonthName = monthName;
    }

    public int getYear() {
        return this.Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public boolean isRamadan() {
        return isRamadan;
    }

    public void setRamadan(boolean isRamadan) {
        this.isRamadan = isRamadan;
    }

    public int getDayOfRamadan() {
        return dayOfRamadan;
    }

    public void setDayOfRamadan(int dayOfRamadan) {
        this.dayOfRamadan = dayOfRamadan;
    }

    public int getDaysUntilRamadan() {
        return daysUntilRamadan;
    }

    public void setDaysUntilRamadan(int daysUntilRamadan) {
        this.daysUntilRamadan = daysUntilRamadan;
    }
}
