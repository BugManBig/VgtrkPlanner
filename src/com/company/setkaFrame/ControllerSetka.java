package com.company.setkaFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.Starter;

import javax.swing.*;

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
        if (selectedLine == -1) {
            return;
        }

        String title = model.getElementFromSetka(selectedLine).getTitle();
        int answer = JOptionPane.showOptionDialog(
                null,
                "Вы уверены, что хотите удалить элемент: \"" + title + "\"?",
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

        model.removeFromSetka(selectedLine);
        updateDataInPlaylist();
    }

    public void handleMenuButtonClick() {
        viewSetka.close();
        Starter.run(model);
    }
}
