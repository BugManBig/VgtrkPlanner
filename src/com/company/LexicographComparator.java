package com.company;

import java.util.Comparator;

public class LexicographComparator implements Comparator<PlanElement> {
    @Override
    public int compare(PlanElement o1, PlanElement o2) {
        int timeFirstElement = o1.getStartTime().getTimeInSeconds();
        int timeSecondElement = o2.getStartTime().getTimeInSeconds();
        return Integer.compare(timeFirstElement, timeSecondElement);
    }
}
