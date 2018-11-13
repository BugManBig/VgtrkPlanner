package com.company.setkaFrame;

import com.company.DataDay;
import com.company.FederalGenerator;
import com.company.Model;
import com.company.PlanElement;
import com.company.finalFrame.ControllerFinal;
import com.company.finalFrame.ViewFinal;

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
        ViewMini viewMini = new ViewMini();

        ControllerMini controllerMini = new ControllerMini();
        controllerMini.setModel(model);
        controllerMini.setViewMini(viewMini);
        controllerMini.setControllerSetka(this);

        viewMini.setControllerMini(controllerMini);
        viewMini.create();
    }

    public void handleEditButtonClick() {
        if (viewSetka.getSelectedLine() == -1) return;
        
        PlanElement planElement = model.getElementFromSetka(viewSetka.getSelectedLine());
        
        ViewMini viewMini = new ViewMini();
        
        ControllerMini controllerMini = new ControllerMini();
        controllerMini.setViewMini(viewMini);
        controllerMini.setModel(model);
        controllerMini.setControllerSetka(this);
        controllerMini.setSelectedListIndex(viewSetka.getSelectedLine());
        
        viewMini.setControllerMini(controllerMini);
        viewMini.create();
        viewMini.setFieldsFromPlanElement(planElement);
    }

    public void handleRemoveButtonClick() {
        int selectedLine = viewSetka.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromSetka(selectedLine);
            updateDataInPlaylist();
        }
    }
    
    public void handleGenerateButtonClick() {
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
    }
}
