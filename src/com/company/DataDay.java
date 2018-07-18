package com.company;

import java.util.GregorianCalendar;
import java.util.List;

public class DataDay {
    private List<PlanElement> federal;
    private List<PlanElement>[] doubles;
    private GregorianCalendar date;

    public DataDay(List<PlanElement> federal, List<PlanElement>[] doubles, GregorianCalendar date) {
        this.federal = federal;
        this.doubles = doubles;
        this.date = date;
    }

    public List<PlanElement> getPlanElementsDay(int from) {
        if (from == 0) {
            return federal;
        }
        return doubles[from - 1];
    }
}
