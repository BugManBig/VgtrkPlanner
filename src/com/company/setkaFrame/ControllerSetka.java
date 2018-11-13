package com.company.setkaFrame;

import com.company.DataDay;
import com.company.FederalGenerator;
import com.company.Model;
import com.company.PlanElement;
import com.company.finalFrame.ControllerFinal;
import com.company.finalFrame.ViewFinal;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerSetka {
    private ViewSetka viewSetka;
    private Model model;

    public void setViewSetka(ViewSetka viewSetka) {
        this.viewSetka = viewSetka;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
    public void updateDataInPlaylist() {
        String[] data = new String[model.getSetkaSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getElementFromSetka(i).getDataStringForSetka();
        }
        viewSetka.setDataToList(data);
    }

    public void selectLine(PlanElement planElement) {
        int i = 0;
        while (model.getElementFromSetka(i) != planElement) i++;
        viewSetka.selectLine(i);
    }
    
    public void handleAddButtonClick() {
        ViewMiniSetka viewMiniSetka = new ViewMiniSetka();

        ControllerMiniSetka controllerMiniSetka = new ControllerMiniSetka();
        controllerMiniSetka.setModel(model);
        controllerMiniSetka.setViewMiniSetka(viewMiniSetka);
        controllerMiniSetka.setControllerSetka(this);

        viewMiniSetka.setControllerMiniSetka(controllerMiniSetka);
        viewMiniSetka.create();
    }

    public void handleEditButtonClick() {
        if (viewSetka.getSelectedLine() == -1) return;
        
        PlanElement planElement = model.getElementFromSetka(viewSetka.getSelectedLine());
        
        ViewMiniSetka viewMiniSetka = new ViewMiniSetka();
        
        ControllerMiniSetka controllerMiniSetka = new ControllerMiniSetka();
        controllerMiniSetka.setViewMiniSetka(viewMiniSetka);
        controllerMiniSetka.setModel(model);
        controllerMiniSetka.setControllerSetka(this);
        controllerMiniSetka.setSelectedListIndex(viewSetka.getSelectedLine());
        
        viewMiniSetka.setControllerMiniSetka(controllerMiniSetka);
        viewMiniSetka.create();
        viewMiniSetka.setFieldsFromPlanElement(planElement);
    }

    public void handleRemoveButtonClick() {
        int selectedLine = viewSetka.getSelectedLine();
        if (selectedLine == -1) return;
        model.removeFromSetka(selectedLine);
        updateDataInPlaylist();
    }
    
    public void handleGenerateButtonClick() {
        GregorianCalendar mondayDate = viewSetka.getDate();
        if (mondayDate == null) {
            JOptionPane.showMessageDialog(null, "Не введена дата понедельника");
            return;
        }
        if (mondayDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            JOptionPane.showMessageDialog(null, "Введённая дата не является понедельником");
            return;
        }

        List<PlanElement>[] federalElements = FederalGenerator.generate(model);
        model.setFederalElements(federalElements);

        DataDay[] dataDays = new DataDay[7];
        for (int i = 0; i < 7; i++) {
            GregorianCalendar date = viewSetka.getDate();
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

        controllerFinal.setDateOfMonday(viewSetka.getDate());
        controllerFinal.updateDataInPlaylist();

        viewSetka.close();
    }
}
