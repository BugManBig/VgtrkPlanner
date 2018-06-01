package com.company;

public enum DaysOfWeek {
    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wen"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("Sun");
    
    private String name;
    
    DaysOfWeek(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}
