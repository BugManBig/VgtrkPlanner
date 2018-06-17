package com.company.setkaFrame;

import com.company.FederalGenerator;
import com.company.Model;
import com.company.PlanElement;
import com.company.federalFrame.ControllerFederalFrame;
import com.company.federalFrame.ViewFederalFrame;
import com.company.miniFrame.ControllerMini;
import com.company.miniFrame.ControllerMiniFrame;
import com.company.miniFrame.ViewMini;
import com.company.miniFrame.ViewMiniFrame;

import java.util.List;

public class ControllerSetkaFrame implements ControllerSetka {
    private ViewSetka viewSetka;
    private Model model;

    @Override
    public void setView(ViewSetka viewSetka) {
        this.viewSetka = viewSetka;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void updateDataInPlaylist() {
        String[] data = new String[model.getSetkaSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getElementFromSetka(i).getDataString();
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
    public void handleAddButtonClick() {
        ViewMini viewMini = new ViewMiniFrame();

        ControllerMini controllerMini = new ControllerMiniFrame();
        controllerMini.setModel(model);
        controllerMini.setView(viewMini);
        controllerMini.setControllerSetka(this);

        viewMini.setController(controllerMini);
        viewMini.create();
    }

    @Override
    public void handleEditButtonClick() {
        if (viewSetka.getSelectedLine() == -1) return;
        
        PlanElement planElement = model.getElementFromSetka(viewSetka.getSelectedLine());
        
        ViewMini viewMini = new ViewMiniFrame();
        
        ControllerMini controllerMini = new ControllerMiniFrame();
        controllerMini.setView(viewMini);
        controllerMini.setModel(model);
        controllerMini.setControllerSetka(this);
        controllerMini.setSelectedListIndex(viewSetka.getSelectedLine());
        
        viewMini.setController(controllerMini);
        viewMini.create();
        viewMini.setFieldsFromPlanElement(planElement);
    }

    @Override
    public void handleRemoveButtonClick() {
        int selectedLine = viewSetka.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromSetka(selectedLine);
        }
        updateDataInPlaylist();
    }

    @Override
    public void handleGenerateButtonClick() {
        List<PlanElement>[] federalElements = FederalGenerator.generate(model);
        model.setFederalElements(federalElements);

        ViewFederalFrame viewFederalFrame = new ViewFederalFrame();

        ControllerFederalFrame controllerFederalFrame = new ControllerFederalFrame();
        controllerFederalFrame.setModel(model);
        controllerFederalFrame.setViewFederalFrame(viewFederalFrame);
        
        viewFederalFrame.setController(controllerFederalFrame);
        viewFederalFrame.create();
        
        controllerFederalFrame.updateDataInPlaylist();
        controllerFederalFrame.setWeekdayInField();
    }
}
