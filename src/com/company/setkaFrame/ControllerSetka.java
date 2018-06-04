package com.company.setkaFrame;

import com.company.Model;

public interface ControllerSetka {
    void setView(ViewSetka viewSetka);

    void setModel(Model model);

    void updateDataInPlaylist();

    void handleAddButtonClick();
    
    void handleEditButtonClick();
    
    void handleRemoveButtonClick();
}
