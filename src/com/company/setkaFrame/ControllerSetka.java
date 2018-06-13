package com.company.setkaFrame;

import com.company.Model;
import com.company.PlanElement;

public interface ControllerSetka {
    void setView(ViewSetka viewSetka);

    void setModel(Model model);

    void updateDataInPlaylist();
    
    void selectLine(PlanElement planElement);

    void handleAddButtonClick();
    
    void handleEditButtonClick();
    
    void handleRemoveButtonClick();
    
    void handleGenerateButtonClick();
}
