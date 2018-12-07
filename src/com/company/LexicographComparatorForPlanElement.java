package com.company;

import java.util.Comparator;

public class LexicographComparatorForPlanElement implements Comparator<PlanElement> {
    private int shift;
    private static final int SECONDS_IN_DAY = 60 * 60 * 24;
    
    @Override
    public int compare(PlanElement o1, PlanElement o2) {
        boolean[] weekdays = o1.getSelectedDays().getSelectionsArray();
        int selectedDay1 = 0;
        int selectedDay2 = 0;
        for (int i = 0; i < 7; i++) {
            if (weekdays[i]) {
                selectedDay1 = i;
                break;
            }
        }
        weekdays = o2.getSelectedDays().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            if (weekdays[i]) {
                selectedDay2 = i;
                break;
            }
        }
        int timeFirstElement = (o1.getStartTime().getTimeInSeconds() + shift * 60 * 60) % SECONDS_IN_DAY;
        int timeSecondElement = (o2.getStartTime().getTimeInSeconds() + shift * 60 * 60) % SECONDS_IN_DAY;
        timeFirstElement = timeFirstElement * 7 + selectedDay1;
        timeSecondElement = timeSecondElement * 7 + selectedDay2;
        return Integer.compare(timeFirstElement, timeSecondElement);
    }

    public LexicographComparatorForPlanElement(int shift) {
        this.shift = shift;
    }

    public LexicographComparatorForPlanElement() {
    }
}
