package com.company.miniFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;
import com.company.setkaFrame.ControllerSetka;

public class ControllerMini {
    private Model model;
    private ViewMini viewMini;
    private ControllerSetka controllerSetka;
    private int selectedListIndex = -1;
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMini(ViewMini viewMini) {
        this.viewMini = viewMini;
    }

    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    public void handleOkButtonClick() {
        PlanElement planElement = new PlanElement(
                viewMini.getTitleText(),
                viewMini.getStartTime(),
                viewMini.getDurationTime(),
                new SelectedDays(viewMini.getCheckboxesState()));
        if (selectedListIndex > -1) {
            model.setElementInSetka(selectedListIndex, planElement);
        } else {
            model.addElementToSetka(planElement);
        }
        viewMini.close();
        controllerSetka.updateDataInPlaylist();
        controllerSetka.selectLine(planElement);
    }

    public void handleCancelButtonClick() {
        viewMini.close();
    }
    
    public void handleSetAllButtonClick() {
        viewMini.setAllCheckboxes();
    }
    
    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
