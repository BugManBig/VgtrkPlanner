package com.company.transitionsFrame;

import com.company.Model;
import com.company.Starter;
import com.company.TransitionElement;

import javax.swing.*;

public class ControllerTransitions {
    private Model model;
    private ViewTransitions viewTransitions;

    public void setModel(Model model) {
        this.model = model;
    }

    public void updateDataInTransitionsList() {
        String[] data = new String[model.getTransitionsSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getTransitionElement(i).getDataString();
        }
        viewTransitions.setDataToList(data);
    }

    public void setViewTransitions(ViewTransitions viewTransitions) {
        this.viewTransitions = viewTransitions;
    }

    public void selectLineAfterCreatingElement(TransitionElement transitionElement) {
        int i = 0;
        while (model.getTransitionElement(i) != transitionElement) i++;
        viewTransitions.selectLine(i);
    }

    private void selectLineAfterCopyElement(TransitionElement transitionElement) {
        int i = 0;
        while (model.getTransitionElement(i).getStartTime().getTimeInSeconds()
                != transitionElement.getStartTime().getTimeInSeconds()) {
            i++;
        }
        while (i < model.getTransitionsSize() && model.getTransitionElement(i).getStartTime().getTimeInSeconds()
                == transitionElement.getStartTime().getTimeInSeconds()) {
            i++;
        }
        viewTransitions.selectLine(i - 1);
    }

    public void handleAddButtonClick() {
        ViewMiniTransitions viewMiniTransitions = new ViewMiniTransitions();

        ControllerMiniTransitions controllerMiniTransitions = new ControllerMiniTransitions();
        controllerMiniTransitions.setModel(model);
        controllerMiniTransitions.setViewMiniTransitions(viewMiniTransitions);
        controllerMiniTransitions.setControllerTransitions(this);

        viewMiniTransitions.setControllerMiniTransitions(controllerMiniTransitions);
        viewMiniTransitions.create();
    }

    public void handleEditButtonClick() {
        if (viewTransitions.getSelectedLine() == -1) return;
        ViewMiniTransitions viewMiniTransitions = new ViewMiniTransitions();

        ControllerMiniTransitions controllerMiniTransitions = new ControllerMiniTransitions();
        controllerMiniTransitions.setModel(model);
        controllerMiniTransitions.setViewMiniTransitions(viewMiniTransitions);
        controllerMiniTransitions.setControllerTransitions(this);
        controllerMiniTransitions.setSelectedListIndex(viewTransitions.getSelectedLine());

        viewMiniTransitions.setControllerMiniTransitions(controllerMiniTransitions);
        viewMiniTransitions.create();
        viewMiniTransitions.setFieldsFromTransitionElement(model.getTransitionElement(viewTransitions.getSelectedLine()));
    }

    public void handleRemoveButtonClick() {
        int selectedLine = viewTransitions.getSelectedLine();
        if (selectedLine == -1) {
            return;
        }

        int answer = JOptionPane.showOptionDialog(
                null,
                "Вы уверены, что хотите удалить выбранный элемент?",
                "Подтвердите действие",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Да", "Нет"},
                null
        );
        if (answer != 0) {
            return;
        }

        model.removeFromTransitions(viewTransitions.getSelectedLine());
        updateDataInTransitionsList();
    }

    public void handleCopyButtonClick() {
        if (viewTransitions.getSelectedLine() == -1) {
            return;
        }
        int selectedLine = viewTransitions.getSelectedLine();
        TransitionElement transitionElement = model.getTransitionElement(selectedLine);
        model.addTransitionElement(transitionElement);
        updateDataInTransitionsList();
        selectLineAfterCopyElement(transitionElement);
    }

    public void handleBackButtonClick() {
        viewTransitions.close();
        Starter.run(model);
    }
}
