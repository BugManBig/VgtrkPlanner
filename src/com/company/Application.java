package com.company;

public class Application {
    public void run() {
        ProjectSettings.createMap();
        Model model = new Model();
        model.startCheck();
        Starter.run(model);
    }
}
