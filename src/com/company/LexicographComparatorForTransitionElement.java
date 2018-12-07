package com.company;

import java.util.Comparator;

public class LexicographComparatorForTransitionElement implements Comparator<TransitionElement> {
    @Override
    public int compare(TransitionElement o1, TransitionElement o2) {
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
        int timeFirstElement = o1.getStartTime().getTimeInSeconds() * 7 + selectedDay1;
        int timeSecondElement = o2.getStartTime().getTimeInSeconds() * 7 + selectedDay2;
        return Integer.compare(timeFirstElement, timeSecondElement);
    }
}
