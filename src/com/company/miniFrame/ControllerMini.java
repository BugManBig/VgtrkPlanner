package com.company.miniFrame;

import com.company.Model;
import com.company.setkaFrame.ControllerSetka;

public interface ControllerMini {
    void setModel(Model model);

    void setView(ViewMini viewMini);
    
    void setControllerSetka(ControllerSetka controllerSetka);
    
    void handleOkButtonClick();
    
    void handleCancelButtonClick();
}
