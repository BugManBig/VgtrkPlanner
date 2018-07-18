package com.company;

import java.util.ArrayList;
import java.util.List;

public class DoublesGenerator {
    public static List<PlanElement>[][] generate(Model model) {
        List<PlanElement>[][] doublesElements = new ArrayList[4][7];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                doublesElements[i][j] = new ArrayList<>();
            }
        }
        
        TransitionElement transitionElement;
        PlanElement planElement;
        PlanElement transitionedElement = null;
        int startTime;
        int endTime;
        int federalElementStartTime;
        int timeDelta;
        for (int i = 0; i < model.getTransitionsSize(); i++) {
            transitionElement = model.getTransitionElement(i);
            startTime = transitionElement.getStartTime().getTimeInSeconds();
            endTime = transitionElement.getEndTime().getTimeInSeconds();
            timeDelta = transitionElement.getTransitionTime().getTimeInSeconds() - transitionElement.getStartTime().getTimeInSeconds();
            for (int weekdayId = 0; weekdayId < 7; weekdayId++) {
                if (!transitionElement.getSelectedDays().getSelectionsArray()[weekdayId]) continue;
                for (int federalElementId = 0; federalElementId < model.getFederalSizeWeekday(weekdayId); federalElementId++) {
                    planElement = model.getFederalElement(weekdayId, federalElementId);
                    federalElementStartTime = planElement.getStartTime().getTimeInSeconds();
                    if (federalElementStartTime >= startTime && federalElementStartTime < endTime) {
                        for (int doubleId = 0; doubleId < 4; doubleId++) {
                            if (transitionElement.getSelectedDoubles().getSelectionsArray()[doubleId]) {
                                try {
                                    transitionedElement = planElement.clone();
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                                transitionedElement.setStartTime(
                                        new Chrono(planElement.getStartTime().getTimeInSeconds() + timeDelta));
                                doublesElements[doubleId][weekdayId].add(transitionedElement);
                            }
                        }
                    }
                }
            }
        }
        return doublesElements;
    }
}
