package com.company.federalFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;

public class ControllerMiniFederal {
    private Model model;
    private ViewMiniFederal viewMiniFederal;
    private ControllerFederal controllerFederal;
    private int selectedListIndex = -1;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMiniFederal(ViewMiniFederal viewMiniFederal) {
        this.viewMiniFederal = viewMiniFederal;
    }

    public void setControllerFederal(ControllerFederal controllerFederal) {
        this.controllerFederal = controllerFederal;
    }

    public void handleOkButtonClick() {
        boolean[] thisDay = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if (controllerFederal.getWeekday() == i) {
                thisDay[i] = true;
            }
        }
        PlanElement planElement = new PlanElement(
                viewMiniFederal.getTitleText(),
                viewMiniFederal.getStartTime(),
                viewMiniFederal.getDurationTime(),
                new SelectedDays(thisDay));
        if (selectedListIndex > -1) {
            model.setFederalElement(controllerFederal.getWeekday(), selectedListIndex, planElement);
        } else {
            model.addFederalElement(controllerFederal.getWeekday(), planElement);
        }
        viewMiniFederal.close();
        controllerFederal.updateDataInPlaylist();
        controllerFederal.selectLine(planElement);
    }

    public void handleCancelButtonClick() {
        viewMiniFederal.close();
    }

    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
