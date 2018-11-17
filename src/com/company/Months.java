package com.company;

public enum Months {
    JANUARY("января"),
    FEBRUARY("февраля"),
    MARCH("марта"),
    APRIL("апреля"),
    MAY("мая"),
    JUNE("июня"),
    JULY("июля"),
    AUGUST("августа"),
    SEPTEMBER("сентября"),
    OCTOBER("октября"),
    NOVEMBER("ноября"),
    DECEMBER("декабря");

    private String name;

    Months(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
