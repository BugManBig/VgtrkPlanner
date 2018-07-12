package com.company;

import java.util.Comparator;

public class LexicographComparatorForTransitionElement implements Comparator<TransitionElement> {
    @Override
    public int compare(TransitionElement o1, TransitionElement o2) {
        return Integer.compare(o1.getStartTime().getTimeInSeconds(), o2.getStartTime().getTimeInSeconds());
    }
}
