package com.company;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<PlanElement> setkaElements = new ArrayList<>();
    private List<PlanElement>[] federalElements;

    public void addElementToSetka(PlanElement planElement) {
        setkaElements.add(planElement);
        sortSetka();
    }

    public PlanElement getElementFromSetka(int id) {
        return setkaElements.get(id);
    }

    public int getSetkaSize() {
        return setkaElements.size();
    }
    
    public void setElementInSetka(int id, PlanElement planElement) {
        setkaElements.set(id, planElement);
        sortSetka();
    }
    
    public void sortSetka() {
        setkaElements.sort(new LexicographComparator());
    }
    
    public void removeFromSetka(int id) {
        setkaElements.remove(id);
    }


    public void setFederalElements(List<PlanElement>[] federalElements) {
        this.federalElements = federalElements;
    }
    
    public PlanElement getFederalElement(int weekday, int element) {
        return federalElements[weekday].get(element);
    }
    
    public int getFederalSizeWeekday(int weekday) {
        return federalElements[weekday].size();
    }
}
