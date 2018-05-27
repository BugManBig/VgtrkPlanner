package com.company;

import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ControllerSetkaFrame;
import com.company.setkaFrame.ViewSetka;
import com.company.setkaFrame.ViewSetkaFrame;

public class Application {
    public void run() {
        Model model = new Model();
        model.addElementToSetka(new PlanElement("Sample", new Chrono(12, 30), new Chrono(0, 30),
                new SelectedDays(new boolean[]{true, true, false, false, false, false, false})));

        ViewSetka viewSetka = new ViewSetkaFrame();

        ControllerSetka controllerSetka = new ControllerSetkaFrame();
        controllerSetka.setModel(model);
        controllerSetka.setView(viewSetka);

        viewSetka.setController(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }
}
