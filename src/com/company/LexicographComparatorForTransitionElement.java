package com.company;

import java.util.Comparator;

public class LexicographComparatorForTransitionElement implements Comparator<TransitionElement> {
    @Override
    public int compare(TransitionElement o1, TransitionElement o2) {
        boolean[] doubles = o1.getSelectedDoubles().getSelectionsArray();
        int selectedDouble1 = 0;
        int selectedDouble2 = 0;
        for (int i = 0; i < 7; i++) {
            if (doubles[i]) {
                selectedDouble1 = i;
                break;
            }
        }
        doubles = o2.getSelectedDoubles().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            if (doubles[i]) {
                selectedDouble2 = i;
                break;
            }
        }
        int timeFirstElement = o1.getStartTime().getTimeInSeconds() * 11 + selectedDouble1;
        int timeSecondElement = o2.getStartTime().getTimeInSeconds() * 11 + selectedDouble2;
        return Integer.compare(timeFirstElement, timeSecondElement);
    }
}
