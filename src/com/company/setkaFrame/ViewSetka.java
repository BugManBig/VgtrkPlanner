package com.company.setkaFrame;

public interface ViewSetka {
    void setController(ControllerSetka controllerSetka);

    void setDataToList(String[] data);

    void create();
    
    int getSelectedLine();
    
    void selectLine(int index);
}
