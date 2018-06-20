package com.company.federalFrame;

import com.company.Model;

public class ControllerFederal {
    private Model model;
    private ViewFederal viewFederal;

    private int weekday = 0;
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewFederal(ViewFederal viewFederal) {
        this.viewFederal = viewFederal;
    }

    public void updateDataInPlaylist() {
        int weekdaySize = model.getFederalSizeWeekday(weekday);
        String[] data = new String[weekdaySize];
        for (int i = 0; i < weekdaySize; i++) {
            data[i] = model.getFederalElement(weekday, i).getDataStringForFederal();
        }
        viewFederal.setDataToList(data);
    }
    
    public void handleEditButtonClick() {
        
    }
    
    public void handleAddButtonClick() {
        
    }
    
    public void handleRemoveButtonClick() {
        
    }
    
    public void handleNextButtonClick() {
        weekday = weekday < 6 ? weekday + 1 : 6;
        updateDataInPlaylist();
        setWeekdayInField();
    }
    
    public void handlePrevButtonClick() {
        weekday = weekday > 0 ? weekday - 1 : 0;
        updateDataInPlaylist();
        setWeekdayInField();
    }
    
    public void setWeekdayInField() {
        viewFederal.setWeekDayText(weekday);
    }
}
