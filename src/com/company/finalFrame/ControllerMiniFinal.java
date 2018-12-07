package com.company.finalFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControllerMiniFinal {
    private Model model;
    private ViewMiniFinal viewMiniFinal;
    private ControllerFinal controllerFinal;
    private int selectedListIndex = -1;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewMiniFinal(ViewMiniFinal viewMiniFinal) {
        this.viewMiniFinal = viewMiniFinal;
    }

    public void setControllerFinal(ControllerFinal controllerFinal) {
        this.controllerFinal = controllerFinal;
    }

    public void handleOkButtonClick() {
        if (viewMiniFinal.getDurationTime().getHours() > 0) {
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
        boolean[] thisDay = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if (controllerFinal.getWeekday() == i) {
                thisDay[i] = true;
            }
        }
        PlanElement planElement = new PlanElement(
                viewMiniFinal.getTitleText(),
                viewMiniFinal.getStartTime(),
                viewMiniFinal.getDurationTime(),
                new SelectedDays(thisDay));
        GregorianCalendar date = new GregorianCalendar(
                controllerFinal.getDateOfMonday().get(Calendar.YEAR),
                controllerFinal.getDateOfMonday().get(Calendar.MONTH),
                controllerFinal.getDateOfMonday().get(Calendar.DAY_OF_MONTH));
        date.add(Calendar.DAY_OF_MONTH, controllerFinal.getWeekday());
        if (selectedListIndex > -1) {
            model.setFinalElement(date, controllerFinal.getMode(), selectedListIndex, planElement);
        } else {
            model.addFinalElement(date, controllerFinal.getMode(), planElement);
        }
        viewMiniFinal.close();
        controllerFinal.updateDataInPlaylist();
        controllerFinal.selectLine(planElement);
        model.saveAllDataDays();
    }

    public void handleCancelButtonClick() {
        viewMiniFinal.close();
    }

    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }
}
