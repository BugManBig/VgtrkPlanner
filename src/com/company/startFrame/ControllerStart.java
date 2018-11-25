package com.company.startFrame;

import com.company.*;
import com.company.finalFrame.ControllerFinal;
import com.company.finalFrame.ViewFinal;
import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;
import com.company.transitionsFrame.ControllerTransitions;
import com.company.transitionsFrame.ViewTransitions;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    public void handleGenerateButtonClick() {
        GregorianCalendar mondayDate = DateInput.run();
        if (mondayDate == null) return;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String fileName = simpleDateFormat.format(mondayDate.getTime()) + ".bin";
        if (FileActions.isExist(ProjectSettings.getParam(ProjectParams.BIN_PATH), fileName)) {
            int answer = JOptionPane.showOptionDialog(
                    null,
                    "Эта неделя уже сгенерирована ранее. Сгенерировать ещё раз?",
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

        List<PlanElement>[] federalElements = FederalGenerator.generate(model);
        model.setFederalElements(federalElements);

        DataDay[] dataDays = new DataDay[7];
        for (int i = 0; i < 7; i++) {
            GregorianCalendar date = (GregorianCalendar) mondayDate.clone();
            date.add(Calendar.DAY_OF_MONTH, i);
            dataDays[i] = new DataDay(model.getFederalElements()[i], null, date);
        }

        model.addDataDays(dataDays);

        ViewFinal viewFinal = new ViewFinal();

        ControllerFinal controllerFinal = new ControllerFinal();
        controllerFinal.setModel(model);
        controllerFinal.setViewFinal(viewFinal);

        viewFinal.setControllerFinal(controllerFinal);
        viewFinal.create();

        controllerFinal.setDateOfMonday(mondayDate);
        controllerFinal.updateDataInPlaylist();

        viewStart.close();
    }

    public void handleTransitionsButtonClick() {
        ViewTransitions viewTransitions = new ViewTransitions();

        ControllerTransitions controllerTransitions = new ControllerTransitions();
        controllerTransitions.setModel(model);
        controllerTransitions.setViewTransitions(viewTransitions);

        viewTransitions.setControllerTransitions(controllerTransitions);
        viewTransitions.create();
        controllerTransitions.updateDataInTransitionsList();

        viewStart.close();
    }
}
