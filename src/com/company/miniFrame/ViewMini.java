package com.company.miniFrame;

import com.company.Chrono;

public interface ViewMini {
    void setController(ControllerMini controllerMini);

    void create();
    
    void close();
    
    String getTitleText();
    
    Chrono getStartTime();
    
    Chrono getDurationTime();
}
