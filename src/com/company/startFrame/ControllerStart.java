package com.company.startFrame;

import com.company.Model;
import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;

public class ControllerStart {
    private ViewStart viewStart;
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewStart(ViewStart viewStart) {
        this.viewStart = viewStart;
    }

    public void handleCreateButtonClick() {
        viewStart.close();

        ViewSetka viewSetka = new ViewSetka();

        ControllerSetka controllerSetka = new ControllerSetka();
        controllerSetka.setModel(model);
        controllerSetka.setViewSetka(viewSetka);

        viewSetka.setControllerSetka(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }
}
