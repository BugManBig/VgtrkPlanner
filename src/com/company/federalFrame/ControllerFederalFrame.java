package com.company.federalFrame;

import com.company.Model;

public class ControllerFederalFrame {
    private Model model;
    private ViewFederalFrame viewFederalFrame;

    private int weekday = 0;
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewFederalFrame(ViewFederalFrame viewFederalFrame) {
        this.viewFederalFrame = viewFederalFrame;
    }

    public void updateDataInPlaylist() {
        int weekdaySize = model.getFederalSizeWeekday(weekday);
        String[] data = new String[weekdaySize];
        for (int i = 0; i < weekdaySize; i++) {
            data[i] = model.getFederalElement(weekday, i).getDataString();
        }
        viewFederalFrame.setDataToList(data);
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
        viewFederalFrame.setWeekDayText(weekday);
    }
}
