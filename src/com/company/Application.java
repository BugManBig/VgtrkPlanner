package com.company;

import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ControllerSetkaFrame;
import com.company.setkaFrame.ViewSetka;
import com.company.setkaFrame.ViewSetkaFrame;

public class Application {
    public void run() {
        Model model = new Model();

        ViewSetka viewSetka = new ViewSetkaFrame();

        ControllerSetka controllerSetka = new ControllerSetkaFrame();
        controllerSetka.setModel(model);
        controllerSetka.setView(viewSetka);

        viewSetka.setController(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }
}
