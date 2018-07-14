package com.company;

public class DoublesGenerator {
    public static void generate(Model model) {
        TransitionElement transitionElement;
        PlanElement planElement;
        int startTime;
        int endTime;
        int federalElementStartTime;
        for (int i = 0; i < model.getTransitionsSize(); i++) {
            transitionElement = model.getTransitionElement(i);
            startTime = transitionElement.getStartTime().getTimeInSeconds();
            endTime = transitionElement.getEndTime().getTimeInSeconds();
            for (int j = 0; j < 7; j++) {
                if (!transitionElement.getSelectedDays().getSelectionsArray()[j]) continue;
                for (int k = 0; k < model.getFederalSizeWeekday(j); k++) {
                    planElement = model.getFederalElement(j, k);
                    federalElementStartTime = planElement.getStartTime().getTimeInSeconds();
                    if (federalElementStartTime >= startTime && federalElementStartTime < endTime) {
                        System.out.println(planElement.getTitle());
                    }
                }
            }
        }
    }
}
