package com.company;

import javax.swing.*;
import java.awt.*;

public class Application {
    public void run() {
        ProjectSettings.createMap();
        Model model = new Model();
        model.startCheck();
        UIManager.put("List.focusCellHighlightBorder", BorderFactory.createLineBorder(Color.decode("#444444"), 1));
        Starter.run(model);
    }
}
