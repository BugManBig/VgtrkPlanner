package com.company.setkaFrame;

import com.company.Model;

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
}
