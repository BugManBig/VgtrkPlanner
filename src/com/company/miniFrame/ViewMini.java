package com.company.miniFrame;

public interface ViewMini {
    void setController(ControllerMini controllerMini);

    void create();
    
    void close();
    
    String getTitleText();
}
