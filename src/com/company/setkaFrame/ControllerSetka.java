package com.company.setkaFrame;

import com.company.Controller;
import com.company.FederalGenerator;
import com.company.Model;
import com.company.PlanElement;
import com.company.federalFrame.ControllerFederal;
import com.company.federalFrame.ViewFederal;
import com.company.miniFrame.ControllerMini;
import com.company.miniFrame.ViewMini;

import java.util.List;

public class ControllerSetka implements Controller {
    private ViewSetka viewSetka;
    private Model model;

    public void setViewSetka(ViewSetka viewSetka) {
        this.viewSetka = viewSetka;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void updateDataInPlaylist() {
        String[] data = new String[model.getSetkaSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getElementFromSetka(i).getDataStringForSetka();
        }
        viewSetka.setDataToList(data);
    }

    @Override
    public void selectLine(PlanElement planElement) {
        int i = 0;
        while (model.getElementFromSetka(i) != planElement) {
            i++;
        }
        viewSetka.selectLine(i);
    }

    @Override
    public int getWeekday() {
        return 0;
    }

    public void handleAddButtonClick() {
        ViewMini viewMini = new ViewMini();

        ControllerMini controllerMini = new ControllerMini();
        controllerMini.setModel(model);
        controllerMini.setViewMini(viewMini);
        controllerMini.setController(this);

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
        controllerMini.setController(this);
        controllerMini.setSelectedListIndex(viewSetka.getSelectedLine());
        
        viewMini.setControllerMini(controllerMini);
        viewMini.create();
        viewMini.setFieldsFromPlanElement(planElement);
    }

    public void handleRemoveButtonClick() {
        int selectedLine = viewSetka.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromSetka(selectedLine);
        }
        updateDataInPlaylist();
    }
    
    public void handleGenerateButtonClick() {
        List<PlanElement>[] federalElements = FederalGenerator.generate(model);
        model.setFederalElements(federalElements);

        ViewFederal viewFederal = new ViewFederal();

        ControllerFederal controllerFederal = new ControllerFederal();
        controllerFederal.setModel(model);
        controllerFederal.setViewFederal(viewFederal);
        
        viewFederal.setControllerFederal(controllerFederal);
        viewFederal.create();
        
        controllerFederal.updateDataInPlaylist();
        controllerFederal.setWeekdayInField();
    }
}
