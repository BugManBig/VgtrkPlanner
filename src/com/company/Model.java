package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class Model {
    private List<PlanElement> setkaElements = new ArrayList<>();
    private List<PlanElement>[] federalElements;
    private List<TransitionElement> transitionElements = new ArrayList<>();
    private List<DataDay> dataDays = new ArrayList<>();

    public void startCheck() {
        if (FileActions.isExist(ProjectSettings.getParam(ProjectParams.BIN_PATH), "Setka.bin")) {
            List<PlanElement> list = (List<PlanElement>) FileActions.load(ProjectSettings.getParam(ProjectParams.BIN_PATH), "Setka.bin");
            setSetkaElements(list);
        }
        if (FileActions.isExist(ProjectSettings.getParam(ProjectParams.BIN_PATH), "Transitions.bin")) {
            List<TransitionElement> list = (List<TransitionElement>) FileActions.load(ProjectSettings.getParam(ProjectParams.BIN_PATH), "Transitions.bin");
            setTransitionElements(list);
        }
    }

    public void addElementToSetka(PlanElement planElement) {
        setkaElements.add(planElement);
        sortSetka();
        FileActions.save(setkaElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Setka.bin");
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
        FileActions.save(setkaElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Setka.bin");
    }

    private void sortSetka() {
        setkaElements.sort(new LexicographComparatorForPlanElement());
    }

    public void removeFromSetka(int id) {
        setkaElements.remove(id);
        FileActions.save(setkaElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Setka.bin");
    }

    private void setSetkaElements(List<PlanElement> setkaElements) {
        this.setkaElements = setkaElements;
    }

    public void setFederalElements(List<PlanElement>[] federalElements) {
        this.federalElements = federalElements;
    }

    public List<PlanElement>[] getFederalElements() {
        return federalElements;
    }

    public void addTransitionElement(TransitionElement transitionElement) {
        transitionElements.add(transitionElement);
        sortTransitions();
        FileActions.save(transitionElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Transitions.bin");
    }
    
    public void setTransitionElement(int id, TransitionElement transitionElement) {
        transitionElements.set(id, transitionElement);
        sortTransitions();
        FileActions.save(transitionElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Transitions.bin");
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
        FileActions.save(transitionElements, ProjectSettings.getParam(ProjectParams.BIN_PATH), "Transitions.bin");
    }

    private void setTransitionElements(List<TransitionElement> transitionElements) {
        this.transitionElements = transitionElements;
    }

    public void addDataDays(DataDay[] dataDays) {
        checkForRewrite(dataDays);
        this.dataDays.addAll(Arrays.asList(dataDays));
        saveAllDataDays();
    }

    public void saveAllDataDays() {
        for (DataDay dataDay : dataDays) {
            saveDataDay(dataDay);
        }
    }

    private void saveDataDay(DataDay dataDay) {
        GregorianCalendar date = dataDay.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String fileName = simpleDateFormat.format(date.getTime()) + ".bin";
        FileActions.save(dataDay, ProjectSettings.getParam(ProjectParams.BIN_PATH), fileName);
    }

    private void checkForRewrite(DataDay[] dataDays) {
        int weeksCount = this.dataDays.size() / 7;
        for (int i = 0; i < weeksCount; i++) {
            if (this.dataDays.get(i * 7).isEquals(dataDays[0].getDate())) {
                for (int j = 0; j < 7; j++) {
                    this.dataDays.remove(i * 7);
                }
                break;
            }
        }
    }

    public boolean loadWeek(GregorianCalendar date) {
        DataDay[] week = new DataDay[7];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String fileName;
        for (int i = 0; i < 7; i++) {
            fileName = simpleDateFormat.format(date.getTime()) + ".bin";
            DataDay dataDay = (DataDay) FileActions.load(ProjectSettings.getParam(ProjectParams.BIN_PATH), fileName);
            if (dataDay == null) {
                return false;
            }
            week[i] = dataDay;
            date.add(Calendar.DAY_OF_MONTH, 1);
        }
        addDataDays(week);
        return true;
    }
    
    public DataDay getDataDay(GregorianCalendar date) {
        for (DataDay dataDay : dataDays) {
            if (dataDay.isEquals(date)) {
                return dataDay;
            }
        }
        return null;
    }

    public boolean isDoublesGenerated(GregorianCalendar date) {
        DataDay dataDay;
        for (int i = 0; i < 7; i++) {
            dataDay = getDataDay(date);
            for (int j = 1; j <= 4; j++) {
                if (dataDay.getPlanElementsDay(j).size() > 0) return true;
            }
            date.add(Calendar.DAY_OF_MONTH, 1);
        }
        return false;
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
