package com.company.transitionsFrame;

import com.company.Model;

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
        
    }
    
    public void handleRemoveButtonClick() {
        
    }
    
    public void handleGenerateButtonClick() {
        
    }
}
