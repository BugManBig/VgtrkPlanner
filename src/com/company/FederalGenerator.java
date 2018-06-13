package com.company;

import java.util.ArrayList;
import java.util.List;

public class FederalGenerator {
    public static List<PlanElement>[] generate(Model model) {
        List<PlanElement>[] elementsByWeek = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            elementsByWeek[i] = new ArrayList<>();
            for (int j = 0; j < model.getSetkaSize(); j++) {
                if (model.getElementFromSetka(j).getSelectedDays().getSelectedDays()[i]) {
                    elementsByWeek[i].add(model.getElementFromSetka(j));
                }
            }
        }
        return elementsByWeek;
    }
}