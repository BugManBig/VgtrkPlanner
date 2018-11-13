package com.company.setkaFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;

public class ControllerMiniSetka {
    private Model model;
    private ViewMiniSetka viewMiniSetka;
    private ControllerSetka controllerSetka;
    private int selectedListIndex = -1;
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMiniSetka(ViewMiniSetka viewMiniSetka) {
        this.viewMiniSetka = viewMiniSetka;
    }

    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    public void handleOkButtonClick() {
        PlanElement planElement = new PlanElement(
                viewMiniSetka.getTitleText(),
                viewMiniSetka.getStartTime(),
                viewMiniSetka.getDurationTime(),
                new SelectedDays(viewMiniSetka.getCheckboxesState()));
        if (selectedListIndex > -1) {
            model.setElementInSetka(selectedListIndex, planElement);
        } else {
            model.addElementToSetka(planElement);
        }
        viewMiniSetka.close();
        controllerSetka.updateDataInPlaylist();
        controllerSetka.selectLine(planElement);
    }

    public void handleCancelButtonClick() {
        viewMiniSetka.close();
    }
    
    public void handleSetAllButtonClick() {
        viewMiniSetka.setAllCheckboxes();
    }
    
    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
