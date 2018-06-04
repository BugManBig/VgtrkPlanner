package com.company;

import java.util.Comparator;

public class LexicographComparator implements Comparator<PlanElement> {
    @Override
    public int compare(PlanElement o1, PlanElement o2) {
        int timeFirstElement = o1.getStartTime().getHours() * 60 * 60
                + o1.getStartTime().getMinutes() * 60
                + o1.getStartTime().getSeconds();
        int timeSecondElement = o2.getStartTime().getHours() * 60 * 60
                + o2.getStartTime().getMinutes() * 60
                + o2.getStartTime().getSeconds();
        return Integer.compare(timeFirstElement, timeSecondElement);
    }
}
