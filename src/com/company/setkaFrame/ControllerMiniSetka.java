package com.company.setkaFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;

import javax.swing.*;

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
        if (viewMiniSetka.isNothingCheckboxesSelected()) {
            JOptionPane.showMessageDialog(null, "Не выбран ни один день недели", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (viewMiniSetka.getDurationTime().getHours() > 0) {
            int answer = JOptionPane.showOptionDialog(
                    null,
                    "Введённая длительность передачи слишком большая. Всё равно продолжить?",
                    "Подтвердите действие",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Да", "Нет"},
                    null
            );
            if (answer != 0) {
                return;
            }
        }
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
