package com.company;

import java.util.ArrayList;
import java.util.List;

public class DoublesGenerator {
    public static List<PlanElement>[] generate(Model model) {
        List<PlanElement>[] doublesElements = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            doublesElements[i] = new ArrayList<>();
        }
        TransitionElement transitionElement;
        PlanElement planElement;
        int startTime;
        int endTime;
        int federalElementStartTime;
        for (int i = 0; i < model.getTransitionsSize(); i++) {
            transitionElement = model.getTransitionElement(i);
            startTime = transitionElement.getStartTime().getTimeInSeconds();
            endTime = transitionElement.getEndTime().getTimeInSeconds();
            for (int weekdayId = 0; weekdayId < 7; weekdayId++) {
                if (!transitionElement.getSelectedDays().getSelectionsArray()[weekdayId]) continue;
                for (int federalElementId = 0; federalElementId < model.getFederalSizeWeekday(weekdayId); federalElementId++) {
                    planElement = model.getFederalElement(weekdayId, federalElementId);
                    federalElementStartTime = planElement.getStartTime().getTimeInSeconds();
                    if (federalElementStartTime >= startTime && federalElementStartTime < endTime) {
                        for (int doubleId = 0; doubleId < 4; doubleId++) {
                            if (transitionElement.getSelectedDoubles().getSelectionsArray()[doubleId]) {
                                doublesElements[doubleId].add(planElement);
                            }
                        }
                    }
                }
            }
        }
        return doublesElements;
    }
}
