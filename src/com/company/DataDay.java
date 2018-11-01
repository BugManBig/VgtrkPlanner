package com.company;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DataDay implements Serializable {
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
    
    public boolean isEquals(GregorianCalendar date) {
        return this.date.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                && this.date.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                && this.date.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH);
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
