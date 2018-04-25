package com.company;

public enum WeekDays {
    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wen"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("Sun");

    private String name;

    WeekDays(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
