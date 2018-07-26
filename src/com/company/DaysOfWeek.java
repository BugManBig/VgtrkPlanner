package com.company;

public enum DaysOfWeek {
    MONDAY("понедельник"),
    TUESDAY("вторник"),
    WEDNESDAY("среда"),
    THURSDAY("четверг"),
    FRIDAY("пятница"),
    SATURDAY("суббота"),
    SUNDAY("воскресенье");
    
    private String name;
    
    DaysOfWeek(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}
