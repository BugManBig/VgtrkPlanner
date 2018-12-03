package com.company;

import javax.swing.*;
import java.awt.*;

public class Application {
    public void run() {
        ProjectSettings.createMap();
        Model model = new Model();
        model.startCheck();
        UIManager.put("List.focusCellHighlightBorder",
                BorderFactory.createLineBorder(
                        Color.decode(ProjectSettings.getParam(ProjectParams.SELECTED_LINE_BACKGROUND_COLOR)), 1)
        );
        Starter.run(model);
    }
}
