package com.company.federalFrame;

import com.company.Controller;
import com.company.Model;
import com.company.PlanElement;
import com.company.miniFederal.ControllerMiniFederal;
import com.company.miniFederal.ViewMiniFederal;
import com.company.transitionsFrame.ControllerTransitions;
import com.company.transitionsFrame.ViewTransitions;

public class ControllerFederal implements Controller {
    private Model model;
    private ViewFederal viewFederal;

    private int weekday = 0;
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewFederal(ViewFederal viewFederal) {
        this.viewFederal = viewFederal;
    }

    @Override
    public void updateDataInPlaylist() {
        int weekdaySize = model.getFederalSizeWeekday(weekday);
        String[] data = new String[weekdaySize];
        for (int i = 0; i < weekdaySize; i++) {
            data[i] = model.getFederalElement(weekday, i).getDataStringForFederal();
        }
        viewFederal.setDataToList(data);
    }

    @Override
    public void selectLine(PlanElement planElement) {
        int i = 0;
        while (model.getFederalElement(weekday, i) != planElement) {
            i++;
        }
        viewFederal.selectLine(i);
    }

    @Override
    public int getWeekday() {
        return weekday;
    }

    public void handleEditButtonClick() {
        if (viewFederal.getSelectedLine() == -1) return;

        PlanElement planElement = model.getFederalElement(weekday, viewFederal.getSelectedLine());

        ViewMiniFederal viewMiniFederal = new ViewMiniFederal();

        ControllerMiniFederal controllerMiniFederal = new ControllerMiniFederal();
        controllerMiniFederal.setViewMiniFederal(viewMiniFederal);
        controllerMiniFederal.setModel(model);
        controllerMiniFederal.setController(this);
        controllerMiniFederal.setSelectedListIndex(viewFederal.getSelectedLine());
        
        viewMiniFederal.setControllerMiniFederal(controllerMiniFederal);
        viewMiniFederal.create();
        viewMiniFederal.setFieldsFromPlanElement(planElement);
    }
    
    public void handleAddButtonClick() {
        ViewMiniFederal viewMiniFederal = new ViewMiniFederal();
        
        ControllerMiniFederal controllerMiniFederal = new ControllerMiniFederal();
        controllerMiniFederal.setModel(model);
        controllerMiniFederal.setViewMiniFederal(viewMiniFederal);
        controllerMiniFederal.setController(this);
        
        viewMiniFederal.setControllerMiniFederal(controllerMiniFederal);
        viewMiniFederal.create();
    }
    
    public void handleRemoveButtonClick() {
        int selectedLine = viewFederal.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromFederal(weekday, selectedLine);
        }
        updateDataInPlaylist();
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
    
    public void handleGenerateButtonClick() {
        ViewTransitions viewTransitions = new ViewTransitions();

        ControllerTransitions controllerTransitions = new ControllerTransitions();
        controllerTransitions.setModel(model);
        controllerTransitions.setViewTransitions(viewTransitions);
        
        viewTransitions.setControllerTransitions(controllerTransitions);
        viewTransitions.create();
    }
    
    public void setWeekdayInField() {
        viewFederal.setWeekDayText(weekday);
    }
}
