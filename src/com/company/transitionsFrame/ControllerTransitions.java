package com.company.transitionsFrame;

import com.company.*;
import com.company.finalFrame.ViewFinal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerTransitions {
    private Model model;
    private ViewTransitions viewTransitions;

    public void setModel(Model model) {
        this.model = model;
    }
    
    public void updateDataInTransitionsList() {
        String[] data = new String[model.getTransitionsSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getTransitionElement(i).getDataString();
        }
        viewTransitions.setDataToList(data);
    }

    public void setViewTransitions(ViewTransitions viewTransitions) {
        this.viewTransitions = viewTransitions;
    }
    
    public void selectLine(TransitionElement transitionElement) {
        int i = 0;
        while (model.getTransitionElement(i) != transitionElement) i++;
        viewTransitions.selectLine(i);
    }
    
    public void handleAddButtonClick() {
        ViewMiniTransitions viewMiniTransitions = new ViewMiniTransitions();
        
        ControllerMiniTransitions controllerMiniTransitions = new ControllerMiniTransitions();
        controllerMiniTransitions.setModel(model);
        controllerMiniTransitions.setViewMiniTransitions(viewMiniTransitions);
        controllerMiniTransitions.setControllerTransitions(this);
        
        viewMiniTransitions.setControllerMiniTransitions(controllerMiniTransitions);
        viewMiniTransitions.create();
    }
    
    public void handleEditButtonClick() {
        if (viewTransitions.getSelectedLine() == -1) return;
        ViewMiniTransitions viewMiniTransitions = new ViewMiniTransitions();

        ControllerMiniTransitions controllerMiniTransitions = new ControllerMiniTransitions();
        controllerMiniTransitions.setModel(model);
        controllerMiniTransitions.setViewMiniTransitions(viewMiniTransitions);
        controllerMiniTransitions.setControllerTransitions(this);
        controllerMiniTransitions.setSelectedListIndex(viewTransitions.getSelectedLine());

        viewMiniTransitions.setControllerMiniTransitions(controllerMiniTransitions);
        viewMiniTransitions.create();
        viewMiniTransitions.setFieldsFromTransitionElement(model.getTransitionElement(viewTransitions.getSelectedLine()));
    }
    
    public void handleRemoveButtonClick() {
        if (viewTransitions.getSelectedLine() == -1) return;
        model.removeFromTransitions(viewTransitions.getSelectedLine());
        updateDataInTransitionsList();
    }
    
    public void handleGenerateButtonClick() {
        GregorianCalendar date = viewTransitions.getDate();
        List<PlanElement>[][] doubles = DoublesGenerator.generate(model);
        DataDay[] dataDays = new DataDay[7];
        for (int i = 0; i < 7; i++) {
            List<PlanElement>[] oneDayDoubles = new ArrayList[4];
            for (int j = 0; j < 4; j++) {
                oneDayDoubles[j] = new ArrayList<>();
                oneDayDoubles[j].addAll(doubles[j][i]);
            }
            dataDays[i] = new DataDay(model.getFederalElements()[i], oneDayDoubles, date);
            date.add(Calendar.DAY_OF_MONTH, 1);
        }
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < dataDays[0].getPlanElementsDay(j).size(); i++) {
                System.out.println(dataDays[0].getPlanElementsDay(j).get(i).getTitle());
            }
            System.out.println("---");
        }
    }
}
