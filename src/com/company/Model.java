package com.company;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Model {
    private List<PlanElement> setkaElements = new ArrayList<>();
    private List<PlanElement>[] federalElements;
    private List<TransitionElement> transitionElements = new ArrayList<>();
    private List<DataDay> dataDays;

    public void addElementToSetka(PlanElement planElement) {
        setkaElements.add(planElement);
        sortSetka();
        FileActions.save(setkaElements, ProjectSettings.BIN_PATH, "Setka.bin");
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
        FileActions.save(setkaElements, ProjectSettings.BIN_PATH, "Setka.bin");
    }

    private void sortSetka() {
        setkaElements.sort(new LexicographComparatorForPlanElement());
    }

    public void removeFromSetka(int id) {
        setkaElements.remove(id);
        FileActions.save(setkaElements, ProjectSettings.BIN_PATH, "Setka.bin");
    }

    public void setSetkaElements(List<PlanElement> setkaElements) {
        this.setkaElements = setkaElements;
    }

    public void setFederalElements(List<PlanElement>[] federalElements) {
        this.federalElements = federalElements;
    }

    public PlanElement getFederalElement(int weekday, int id) {
        return federalElements[weekday].get(id);
    }

    public List<PlanElement>[] getFederalElements() {
        return federalElements;
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
        FileActions.save(transitionElements, ProjectSettings.BIN_PATH, "Transitions.bin");
    }
    
    public void setTransitionElement(int id, TransitionElement transitionElement) {
        transitionElements.set(id, transitionElement);
        sortTransitions();
        FileActions.save(transitionElements, ProjectSettings.BIN_PATH, "Transitions.bin");
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
        FileActions.save(transitionElements, ProjectSettings.BIN_PATH, "Transitions.bin");
    }

    public void setTransitionElements(List<TransitionElement> transitionElements) {
        this.transitionElements = transitionElements;
    }

    public void addDataDays(DataDay[] dataDays) {
        this.dataDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            this.dataDays.add(dataDays[i]);
        }
    }
    
    public DataDay getDataDay(GregorianCalendar date) {
        for (int i = 0; i < dataDays.size(); i++) {
            if (dataDays.get(i).isEquals(date)) {
                return dataDays.get(i);
            }
        }
        return null;
    }
    
    public void sortDataDay(GregorianCalendar date, int mode) {
        int shift = 0;
        if (mode == 1) shift = 3;
        if (mode == 2) shift = 1;
        getDataDay(date).getPlanElementsDay(mode).sort(new LexicographComparatorForPlanElement(shift));
    }
    
    public void addFinalElement(GregorianCalendar date, int mode, PlanElement planElement) {
        getDataDay(date).getPlanElementsDay(mode).add(planElement);
        sortDataDay(date, mode);
    }
    
    public void setFinalElement(GregorianCalendar date, int mode, int listIndex, PlanElement planElement) {
        getDataDay(date).getPlanElementsDay(mode).set(listIndex, planElement);
        sortDataDay(date, mode);
    }
    
    public void removeFromFinal(GregorianCalendar date, int mode, int id) {
        getDataDay(date).getPlanElementsDay(mode).remove(id);
    }
}
