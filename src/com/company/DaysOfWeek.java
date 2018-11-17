package com.company;

public enum DaysOfWeek {
    MONDAY("ПОНЕДЕЛЬНИК", "пнд"),
    TUESDAY("ВТОРНИК", "втр"),
    WEDNESDAY("СРЕДА", "срд"),
    THURSDAY("ЧЕТВЕРГ", "чтв"),
    FRIDAY("ПЯТНИЦА", "птн"),
    SATURDAY("СУББОТА", "сбт"),
    SUNDAY("ВОСКРЕСЕНЬЕ", "вск");

    private String fullName;
    private String shortName;

    DaysOfWeek(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }
}
