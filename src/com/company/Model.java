package com.company;

import java.util.ArrayList;

public class Model {
    private ArrayList<PlanElement> setkaElements = new ArrayList<>();

    public void addElementToSetka(PlanElement planElement) {
        setkaElements.add(planElement);
    }

    public PlanElement getElementFromSetka(int id) {
        return setkaElements.get(id);
    }

    public int getSize() {
        return setkaElements.size();
    }
    
    public void setElementInSetka(int id, PlanElement planElement) {
        setkaElements.set(id, planElement);
    }
}
