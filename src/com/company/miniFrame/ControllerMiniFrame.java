package com.company.miniFrame;

import com.company.Chrono;
import com.company.Model;
import com.company.PlanElement;
import com.company.SelectedDays;
import com.company.setkaFrame.ControllerSetka;

public class ControllerMiniFrame implements ControllerMini {
    private Model model;
    private ViewMini viewMini;
    private ControllerSetka controllerSetka;
    
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(ViewMini viewMini) {
        this.viewMini = viewMini;
    }

    @Override
    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    @Override
    public void handleOkButtonClick() {
        viewMini.close();
        model.addElementToSetka(new PlanElement(viewMini.getTitleText(), new Chrono(0, 0), new Chrono(50, 5), new SelectedDays(new boolean[7])));
        controllerSetka.updateDataInPlaylist();
    }
}
