package com.company.startFrame;

import com.company.DateInput;
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
        GregorianCalendar mondayDate = DateInput.run();
        if (mondayDate == null) return;

        boolean isLoaded = model.loadWeek((GregorianCalendar) mondayDate.clone());
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Неделя ещё не сгенерирована", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        viewStart.close();

        ViewFinal viewFinal = new ViewFinal();

        ControllerFinal controllerFinal = new ControllerFinal();
        controllerFinal.setModel(model);
        controllerFinal.setViewFinal(viewFinal);

        viewFinal.setControllerFinal(controllerFinal);
        viewFinal.create();

        controllerFinal.setDateOfMonday(mondayDate);
        controllerFinal.updateDataInPlaylist();
    }
}
