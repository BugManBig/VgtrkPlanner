package com.company;

import java.util.Comparator;

public class LexicographComparatorForPlanElement implements Comparator<PlanElement> {
    private int shift;
    private static final int SECONDS_IN_DAY = 60 * 60 * 24;
    
    @Override
    public int compare(PlanElement o1, PlanElement o2) {
        int timeFirstElement = (o1.getStartTime().getTimeInSeconds() + shift * 60 * 60) % SECONDS_IN_DAY;
        int timeSecondElement = (o2.getStartTime().getTimeInSeconds() + shift * 60 * 60) % SECONDS_IN_DAY;
        return Integer.compare(timeFirstElement, timeSecondElement);
    }

    public LexicographComparatorForPlanElement(int shift) {
        this.shift = shift;
    }

    public LexicographComparatorForPlanElement() {
    }
}
