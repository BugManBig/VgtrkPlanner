package com.company;

import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;

public class Application {
    public void run() {
        Model model = new Model();

        ViewSetka viewSetka = new ViewSetka();

        ControllerSetka controllerSetka = new ControllerSetka();
        controllerSetka.setModel(model);
        controllerSetka.setViewSetka(viewSetka);

        viewSetka.setControllerSetka(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }
}
