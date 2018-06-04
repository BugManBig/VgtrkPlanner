package com.company.miniFrame;

import com.company.Chrono;
import com.company.PlanElement;

public interface ViewMini {
    void setController(ControllerMini controllerMini);

    void create();
    
    void close();
    
    String getTitleText();
    
    Chrono getStartTime();
    
    Chrono getDurationTime();
    
    boolean[] getCheckboxesState();
    
    void setAllCheckboxes();
    
    void setFieldsFromPlanElement(PlanElement planElement);
}
