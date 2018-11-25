package com.company.finalFrame;

import com.company.*;
import com.company.transitionsFrame.ControllerTransitions;
import com.company.transitionsFrame.ViewTransitions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerFinal {
    private ViewFinal viewFinal;
    private Model model;
    private int mode = 0;
    private int dayOfWeek = 0;
    private GregorianCalendar dateOfMonday;

    public void setViewFinal(ViewFinal viewFinal) {
        this.viewFinal = viewFinal;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setDateOfMonday(GregorianCalendar dateOfMonday) {
        this.dateOfMonday = dateOfMonday;
    }
    
    public void updateDataInPlaylist() {
        viewFinal.setWeekDayText(dayOfWeek);
        viewFinal.setDoubleText(mode);
        
        DataDay dataDay = model.getDataDay(getCurrentDate());
        if (dataDay.getPlanElementsDay(mode) == null) {
            viewFinal.setDataToList(new String[]{});
            return;
        }
        String[] data = new String[dataDay.getPlanElementsDay(mode).size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = dataDay.getPlanElementsDay(mode).get(i).getDataStringForFinal();
        }
        viewFinal.setDataToList(data);
        viewFinal.setDateAtFrameTitle(getCurrentDate());
    }

    public void handleEditButtonClick() {
        if (viewFinal.getSelectedLine() == -1) return;

        ViewMiniFinal viewMiniFinal = new ViewMiniFinal();

        ControllerMiniFinal controllerMiniFinal = new ControllerMiniFinal();
        controllerMiniFinal.setModel(model);
        controllerMiniFinal.setViewMiniFinal(viewMiniFinal);
        controllerMiniFinal.setControllerFinal(this);
        controllerMiniFinal.setSelectedListIndex(viewFinal.getSelectedLine());

        viewMiniFinal.setControllerMiniFinal(controllerMiniFinal);
        viewMiniFinal.create();
        viewMiniFinal.setFieldsFromPlanElement(model.getDataDay(
                getCurrentDate()).getPlanElementsDay(mode).get(viewFinal.getSelectedLine()));
        model.saveAllDataDays();
    }

    public void handleAddButtonClick() {
        ViewMiniFinal viewMiniFinal = new ViewMiniFinal();
        
        ControllerMiniFinal controllerMiniFinal = new ControllerMiniFinal();
        controllerMiniFinal.setModel(model);
        controllerMiniFinal.setViewMiniFinal(viewMiniFinal);
        controllerMiniFinal.setControllerFinal(this);
        
        viewMiniFinal.setControllerMiniFinal(controllerMiniFinal);
        viewMiniFinal.create();
    }

    public void handleRemoveButtonClick() {
        int selectedLine = viewFinal.getSelectedLine();
        if (selectedLine > -1) {
            model.removeFromFinal(getCurrentDate(), mode, selectedLine);
            updateDataInPlaylist();
        }
        model.saveAllDataDays();
    }

    public void handlePrevDayButtonClick() {
        dayOfWeek = dayOfWeek > 0 ? dayOfWeek - 1 : 6;
        updateDataInPlaylist();
    }

    public void handleNextDayButtonClick() {
        dayOfWeek = dayOfWeek < 6 ? dayOfWeek + 1 : 0;
        updateDataInPlaylist();
    }
    
    public void handlePrevDoubleButtonClick() {
        mode = mode > 0 ? mode - 1 : 4;
        updateDataInPlaylist();
    }
    
    public void handleNextDoubleButtonClick() {
        mode = mode < 4 ? mode + 1 : 0;
        updateDataInPlaylist();
    }
    
    public void handleDocumentationButtonClick() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String reversedDate = simpleDateFormat.format(dateOfMonday.getTime());
        createDocumentation(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH), reversedDate + " " + "Федеральное", 0);
        createDocumentation(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH), reversedDate + " " + "Дубль-1", 1);
        createDocumentation(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH), reversedDate + " " + "Дубль-2", 2);
        createDocumentation(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH), reversedDate + " " + "Дубль-3", 3);
        createDocumentation(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH), reversedDate + " " + "Дубль-4", 4);
    }

    public void handleTransitionsButtonClick() {
        setVisible(false);

        ViewTransitions viewTransitions = new ViewTransitions();

        ControllerTransitions controllerTransitions = new ControllerTransitions();
        controllerTransitions.setModel(model);
        controllerTransitions.setViewTransitions(viewTransitions);
        controllerTransitions.setDateOfMonday(dateOfMonday);
        controllerTransitions.setControllerFinal(this);

        viewTransitions.setControllerTransitions(controllerTransitions);
        viewTransitions.create();
        controllerTransitions.updateDataInTransitionsList();
    }

    public void handleMenuButtonClick() {
        viewFinal.close();
        Starter.run(model);
    }
    
    private void createDocumentation(String path, String name, int mode) {
        GregorianCalendar shiftedDate = new GregorianCalendar(
                dateOfMonday.get(Calendar.YEAR),
                dateOfMonday.get(Calendar.MONTH),
                dateOfMonday.get(Calendar.DAY_OF_MONTH));
        List<String> data = new ArrayList<>();
        data.add("ПРОГРАММА ПЕРЕДАЧ `РАДИО РОССИИ`");
        if (mode > 0) {
            data.add("РРД-" + mode);
        }
        data.add("на неделю с "
                + twoDigitsNumber(dateOfMonday.get(Calendar.DAY_OF_MONTH)) + "."
                + twoDigitsNumber((dateOfMonday.get(Calendar.MONTH) + 1)) + "."
                + String.valueOf(dateOfMonday.get(Calendar.YEAR)).substring(2));
        data.add("(время московское)");
        data.add("");
        data.add("");
        String line;
        List<PlanElement> list;
        for (int i = 0; i < 7; i++) {
            list = model.getDataDay(shiftedDate).getPlanElementsDay(mode);
            data.add(shiftedDate.get(Calendar.DAY_OF_MONTH) + " "
                    + (Months.values()[shiftedDate.get(Calendar.MONTH)]) + " - "
                    + DaysOfWeek.values()[i].toString());
            for (PlanElement planElement : list) {
                line = planElement.getStartTime().getTimeStringSmall();
                line += " " + planElement.getTitle();
                data.add(line);
            }
            data.add("");
            shiftedDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        FileActions.createFile(path + "\\" + name + ".txt", data);
    }

    public void setVisible(boolean b) {
        viewFinal.setVisible(b);
    }
    
    private String twoDigitsNumber(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }

    public int getWeekday() {
        return dayOfWeek;
    }

    public GregorianCalendar getDateOfMonday() {
        return dateOfMonday;
    }
    
    public int getMode() {
        return mode;
    }
    
    private GregorianCalendar getCurrentDate() {
        GregorianCalendar currentDate = new GregorianCalendar(
                dateOfMonday.get(Calendar.YEAR),
                dateOfMonday.get(Calendar.MONTH),
                dateOfMonday.get(Calendar.DAY_OF_MONTH));
        currentDate.add(Calendar.DAY_OF_MONTH, dayOfWeek);
        return currentDate;
    }

    public void selectLine(PlanElement planElement) {
        int i = 0;
        while (model.getDataDay(getCurrentDate()).getPlanElementsDay(mode).get(i) != planElement) {
            i++;
        }
        viewFinal.selectLine(i);
    }
}
