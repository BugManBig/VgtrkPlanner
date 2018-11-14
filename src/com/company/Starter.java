package com.company;

import com.company.startFrame.ControllerStart;
import com.company.startFrame.ViewStart;

public class Starter {
    public static void run(Model model) {
        ViewStart viewStart = new ViewStart();

        ControllerStart controllerStart = new ControllerStart();
        controllerStart.setModel(model);
        controllerStart.setViewStart(viewStart);

        viewStart.setControllerStart(controllerStart);
        viewStart.create();
    }
}
