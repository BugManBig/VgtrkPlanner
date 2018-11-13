package com.company.startFrame;

import com.company.Model;
import com.company.finalFrame.ControllerFinal;
import com.company.finalFrame.ViewFinal;
import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControllerStart {
    private ViewStart viewStart;
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewStart(ViewStart viewStart) {
        this.viewStart = viewStart;
    }

    public void handleCreateButtonClick() {
        viewStart.close();

        ViewSetka viewSetka = new ViewSetka();

        ControllerSetka controllerSetka = new ControllerSetka();
        controllerSetka.setModel(model);
        controllerSetka.setViewSetka(viewSetka);

        viewSetka.setControllerSetka(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }

    public void handleLoadButtonClick() {
        String dateText = viewStart.getDateText();
        if (dateText.equals("")) {
            JOptionPane.showMessageDialog(null, "Не введена дата понедельника");
            return;
        }
        int day = Integer.parseInt(dateText.substring(0, 2));
        int month = Integer.parseInt(dateText.substring(3, 5)) - 1;
        int year = Integer.parseInt(dateText.substring(6, 8)) + 2000;
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        if (date.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            JOptionPane.showMessageDialog(null, "Введённая дата не является понедельником");
            return;
        }
        boolean isLoaded = model.loadWeek((GregorianCalendar) date.clone());
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Нет такой недели");
            return;
        }

        viewStart.close();

        ViewFinal viewFinal = new ViewFinal();

        ControllerFinal controllerFinal = new ControllerFinal();
        controllerFinal.setModel(model);
        controllerFinal.setViewFinal(viewFinal);

        viewFinal.setControllerFinal(controllerFinal);
        viewFinal.create();

        controllerFinal.setDateOfMonday(date);
        controllerFinal.updateDataInPlaylist();
    }
}
