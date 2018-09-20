package com.company;

import com.company.setkaFrame.ControllerSetka;
import com.company.setkaFrame.ViewSetka;

import java.util.List;

public class Application {
    public void run() {
        Model model = new Model();
        if (FileActions.isExist(ProjectSettings.BIN_PATH, "Setka.bin")) {
            List<PlanElement> list = (List<PlanElement>) FileActions.load(ProjectSettings.BIN_PATH, "Setka.bin");
            model.setSetkaElements(list);
        }
        if (FileActions.isExist(ProjectSettings.BIN_PATH, "Transitions.bin")) {
            List<TransitionElement> list = (List<TransitionElement>) FileActions.load(ProjectSettings.BIN_PATH, "Transitions.bin");
            model.setTransitionElements(list);
        }

        ViewSetka viewSetka = new ViewSetka();

        ControllerSetka controllerSetka = new ControllerSetka();
        controllerSetka.setModel(model);
        controllerSetka.setViewSetka(viewSetka);

        viewSetka.setControllerSetka(controllerSetka);

        viewSetka.create();
        controllerSetka.updateDataInPlaylist();
    }
}
