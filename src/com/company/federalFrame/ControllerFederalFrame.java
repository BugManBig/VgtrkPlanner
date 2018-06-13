package com.company.federalFrame;

import com.company.Model;
import com.company.PlanElement;
import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;

public class ControllerFederalFrame implements ControllerSetka {
    private ViewSetka viewFederal;
    private Model model;
    
    protected int weekday = 0;
    
    @Override
    public void setView(ViewSetka viewFederal) {
        this.viewFederal = viewFederal;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void updateDataInPlaylist() {
        int weekdaySize = model.getFederalSizeWeekday(weekday);
        String[] data = new String[weekdaySize];
        for (int i = 0; i < weekdaySize; i++) {
            data[i] = model.getFederalElement(weekday, i).getDataString();
        }
        viewFederal.setDataToList(data);
    }

    @Override
    public void selectLine(PlanElement planElement) {
        
    }

    @Override
    public void handleAddButtonClick() {

    }

    @Override
    public void handleEditButtonClick() {

    }

    @Override
    public void handleRemoveButtonClick() {

    }

    @Override
    public void handleGenerateButtonClick() {

    }
}
