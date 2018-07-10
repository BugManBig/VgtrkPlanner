package com.company;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<PlanElement> setkaElements = new ArrayList<>();
    private List<PlanElement>[] federalElements;
    private List<TransitionElement> transitionElements = new ArrayList<>();

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

    private void sortSetka() {
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
    
    public void setFederalElement(int weekday, int id, PlanElement planElement) {
        federalElements[weekday].set(id, planElement);
        sortFederal(weekday);
    }
    
    public void addFederalElement(int weekday, PlanElement planElement) {
        federalElements[weekday].add(planElement);
        sortFederal(weekday);
    }
    
    public void removeFromFederal(int weekday, int id) {
        federalElements[weekday].remove(id);
    }
    
    private void sortFederal(int weekday) {
        federalElements[weekday].sort(new LexicographComparator());
    }
    
    public void addTransitionElement(TransitionElement transitionElement) {
        transitionElements.add(transitionElement);
    }
    
    public int getTransitionSize() {
        return transitionElements.size();
    }
    
    public TransitionElement getTransitionElement(int id) {
        return transitionElements.get(id);
    }
}
