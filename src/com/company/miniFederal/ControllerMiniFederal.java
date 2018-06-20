package com.company.miniFederal;

import com.company.Controller;
import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;

public class ControllerMiniFederal {
    private Model model;
    private ViewMiniFederal viewMiniFederal;
    private Controller controller;
    private int selectedListIndex = -1;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMiniFederal(ViewMiniFederal viewMiniFederal) {
        this.viewMiniFederal = viewMiniFederal;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleOkButtonClick() {
        boolean[] thisDay = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if (controller.getWeekday() == i) {
                thisDay[i] = true;
            }
        }
        PlanElement planElement = new PlanElement(
                viewMiniFederal.getTitleText(),
                viewMiniFederal.getStartTime(),
                viewMiniFederal.getDurationTime(),
                new SelectedDays(thisDay));
        if (selectedListIndex > -1) {
            model.setFederalElement(controller.getWeekday(), selectedListIndex, planElement);
        } else {
            model.addFederalElement(controller.getWeekday(), planElement);
        }
        viewMiniFederal.close();
        controller.updateDataInPlaylist();
        controller.selectLine(planElement);
    }

    public void handleCancelButtonClick() {
        viewMiniFederal.close();
    }

    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
