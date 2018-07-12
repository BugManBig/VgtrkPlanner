package com.company.transitionsFrame;

import com.company.Model;
import com.company.TransitionElement;

public class ControllerTransitions {
    private Model model;
    private ViewTransitions viewTransitions;

    public void setModel(Model model) {
        this.model = model;
    }
    
    public void updateDataInTransitionsList() {
        String[] data = new String[model.getTransitionSize()];
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
        
    }
}
