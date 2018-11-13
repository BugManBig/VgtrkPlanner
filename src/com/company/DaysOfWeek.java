package com.company;

public enum DaysOfWeek {
    MONDAY("пнд"),
    TUESDAY("втр"),
    WEDNESDAY("срд"),
    THURSDAY("чтв"),
    FRIDAY("птн"),
    SATURDAY("сбт"),
    SUNDAY("вск");
    
    private String name;
    
    DaysOfWeek(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}
