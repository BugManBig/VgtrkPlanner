package com.company.transitionsFrame;

import com.company.*;
import com.company.finalFrame.ControllerFinal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerTransitions {
    private Model model;
    private ViewTransitions viewTransitions;
    private GregorianCalendar dateOfMonday;
    private ControllerFinal controllerFinal;

    public void setControllerFinal(ControllerFinal controllerFinal) {
        this.controllerFinal = controllerFinal;
    }

    public void setDateOfMonday(GregorianCalendar dateOfMonday) {
        this.dateOfMonday = dateOfMonday;
    }

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
        int selectedLine = viewTransitions.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromTransitions(viewTransitions.getSelectedLine());
            updateDataInTransitionsList();
        }
    }
    
    public void handleGenerateButtonClick() {
        List<PlanElement>[][] doubles = DoublesGenerator.generate(model, (GregorianCalendar) dateOfMonday.clone());
        DataDay[] dataDays = new DataDay[7];
        for (int i = 0; i < 7; i++) {
            List<PlanElement>[] oneDayDoubles = new ArrayList[4];
            for (int j = 0; j < 4; j++) {
                oneDayDoubles[j] = new ArrayList<>();
                oneDayDoubles[j].addAll(doubles[j][i]);
            }
            GregorianCalendar date = (GregorianCalendar) dateOfMonday.clone();
            date.add(Calendar.DAY_OF_MONTH, i);
            dataDays[i] = new DataDay(model.getDataDay(date).getPlanElementsDay(0), oneDayDoubles, date);
        }
        
        model.addDataDays(dataDays);
        GregorianCalendar date = (GregorianCalendar) dateOfMonday.clone();
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j <= 4; j++) {
                model.sortDataDay(date, j);
            }
            date.add(Calendar.DAY_OF_MONTH, 1);
        }

        viewTransitions.close();
        controllerFinal.setVisible(true);
        controllerFinal.updateDataInPlaylist();
    }

    public void handleBackButtonClick() {
        viewTransitions.close();
        controllerFinal.setVisible(true);
    }
}
