package com.company;

import com.company.startFrame.ControllerStart;
import com.company.startFrame.ViewStart;

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

        ViewStart viewStart = new ViewStart();

        ControllerStart controllerStart = new ControllerStart();
        controllerStart.setModel(model);
        controllerStart.setViewStart(viewStart);

        viewStart.setControllerStart(controllerStart);
        viewStart.create();
    }
}
