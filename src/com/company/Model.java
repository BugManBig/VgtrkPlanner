package com.company;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<PlanElement> setkaElements = new ArrayList<>();
    private List<PlanElement>[] federalElements;
    private List<TransitionElement> transitionElements = new ArrayList<>();
    private List<PlanElement>[] doublesElements;

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
        setkaElements.sort(new LexicographComparatorForPlanElement());
    }

    public void removeFromSetka(int id) {
        setkaElements.remove(id);
    }

    public void setFederalElements(List<PlanElement>[] federalElements) {
        this.federalElements = federalElements;
    }

    public PlanElement getFederalElement(int weekday, int id) {
        return federalElements[weekday].get(id);
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
        federalElements[weekday].sort(new LexicographComparatorForPlanElement());
    }
    
    public void addTransitionElement(TransitionElement transitionElement) {
        transitionElements.add(transitionElement);
        sortTransitions();
    }
    
    public void setTransitionElement(int id, TransitionElement transitionElement) {
        transitionElements.set(id, transitionElement);
        sortTransitions();
    }
    
    public int getTransitionsSize() {
        return transitionElements.size();
    }
    
    public TransitionElement getTransitionElement(int id) {
        return transitionElements.get(id);
    }
    
    private void sortTransitions() {
        transitionElements.sort(new LexicographComparatorForTransitionElement());
    }
    
    public void removeFromTransitions(int id) {
        transitionElements.remove(id);
    }

    public void setDoublesElements(List<PlanElement>[] doublesElements) {
        this.doublesElements = doublesElements;
    }
}
