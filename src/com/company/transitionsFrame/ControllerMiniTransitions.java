package com.company.transitionsFrame;

import com.company.Model;
import com.company.SelectedDays;
import com.company.SelectedDoubles;
import com.company.TransitionElement;

import javax.swing.*;

public class ControllerMiniTransitions {
    private Model model;
    private ViewMiniTransitions viewMiniTransitions;
    private ControllerTransitions controllerTransitions;
    private int selectedListIndex = -1;

    public void setSelectedListIndex(int selectedListIndex) {
        this.selectedListIndex = selectedListIndex;
    }

    public void setControllerTransitions(ControllerTransitions controllerTransitions) {
        this.controllerTransitions = controllerTransitions;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMiniTransitions(ViewMiniTransitions viewMiniTransitions) {
        this.viewMiniTransitions = viewMiniTransitions;
    }

    public void handleOkButtonClick() {
        if (viewMiniTransitions.isNothingWeekCheckboxesSelected()) {
            JOptionPane.showMessageDialog(null, "Не выбран ни один день недели");
            return;
        }
        if (viewMiniTransitions.isNothingDoublesCheckboxesSelected()) {
            JOptionPane.showMessageDialog(null, "Не выбран ни один дубль");
            return;
        }
        TransitionElement transitionElement = new TransitionElement(
                viewMiniTransitions.getStartTime(),
                viewMiniTransitions.getEndTime(),
                viewMiniTransitions.getTransitionTime(),
                new SelectedDays(viewMiniTransitions.getWeekdaysCheckboxes()),
                new SelectedDoubles(viewMiniTransitions.getDoublesCheckboxes()));
        if (selectedListIndex == -1) {
            model.addTransitionElement(transitionElement);
        } else {
            model.setTransitionElement(selectedListIndex, transitionElement);
        }
        viewMiniTransitions.close();
        controllerTransitions.updateDataInTransitionsList();
        controllerTransitions.selectLineAfterCreatingElement(transitionElement);
    }

    public void handleCancelButtonClick() {
        viewMiniTransitions.close();
    }

    public void handleSetAllButtonClick() {
        viewMiniTransitions.setAllDaysCheckboxes();
    }
}
