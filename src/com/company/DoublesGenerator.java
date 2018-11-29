package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DoublesGenerator {
    public static List<PlanElement>[][] generate(Model model, GregorianCalendar dateOfMonday) {
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
            if (endTime == 0) {
                endTime = 24 * 60 * 60;
            }
            timeDelta = transitionElement.getTransitionTime().getTimeInSeconds() - transitionElement.getStartTime().getTimeInSeconds();
            for (int weekdayId = 0; weekdayId < 7; weekdayId++) {
                GregorianCalendar date = (GregorianCalendar) dateOfMonday.clone();
                date.add(Calendar.DAY_OF_MONTH, weekdayId);
                if (!transitionElement.getSelectedDays().getSelectionsArray()[weekdayId]) continue;
                for (int federalElementId = 0; federalElementId < model.getDataDay(date).getPlanElementsDay(0).size(); federalElementId++) {
                    planElement = model.getDataDay(date).getPlanElementsDay(0).get(federalElementId);
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
                                int offsetToWeekday = transitionElement.getOffsetToWeekday();
                                int resultWeekdayIndex = offsetToWeekday == -1 ? weekdayId : offsetToWeekday;
                                doublesElements[doubleId][resultWeekdayIndex].add(transitionedElement);
                            }
                        }
                    }
                }
            }
        }
        return doublesElements;
    }
}
