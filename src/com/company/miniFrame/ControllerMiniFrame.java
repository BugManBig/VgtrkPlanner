package com.company.miniFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;
import com.company.setkaFrame.ControllerSetka;

public class ControllerMiniFrame implements ControllerMini {
    private Model model;
    private ViewMini viewMini;
    private ControllerSetka controllerSetka;
    private int selectedListIndex = -1;
    
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(ViewMini viewMini) {
        this.viewMini = viewMini;
    }

    @Override
    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    @Override
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

    @Override
    public void handleCancelButtonClick() {
        viewMini.close();
    }

    @Override
    public void handleSetAllButtonClick() {
        viewMini.setAllCheckboxes();
    }

    @Override
    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
