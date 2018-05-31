package com.company.setkaFrame;

import com.company.Model;
import com.company.miniFrame.ControllerMini;
import com.company.miniFrame.ControllerMiniFrame;
import com.company.miniFrame.ViewMini;
import com.company.miniFrame.ViewMiniFrame;

public class ControllerSetkaFrame implements ControllerSetka {
    private ViewSetka viewSetka;
    private Model model;

    @Override
    public void setView(ViewSetka viewSetka) {
        this.viewSetka = viewSetka;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void updateDataInPlaylist() {
        String[] data = new String[model.getSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getElementFromSetka(i).getDataString();
        }
        viewSetka.setDataToList(data);
    }

    @Override
    public void handleAddButtonClick() {
        ViewMini viewMini = new ViewMiniFrame();

        ControllerMini controllerMini = new ControllerMiniFrame();
        controllerMini.setModel(model);
        controllerMini.setView(viewMini);
        controllerMini.setControllerSetka(this);

        viewMini.setController(controllerMini);
        viewMini.create();
    }
}
