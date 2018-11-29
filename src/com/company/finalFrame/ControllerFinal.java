package com.company.finalFrame;

import com.company.*;

import javax.swing.*;
import java.io.File;
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

        if (new File(ProjectSettings.getParam(ProjectParams.OUTPUT_PATH)
                + "\\" + reversedDate + " Федеральное.txt").exists()) {
            int answer = JOptionPane.showOptionDialog(
                    null,
                    "Отчёты уже были созданы ранее. Создать их ещё раз?",
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
        }

        createDocumentation(reversedDate + " " + "Федеральное", 0);
        createDocumentation(reversedDate + " " + "Дубль-1", 1);
        createDocumentation(reversedDate + " " + "Дубль-2", 2);
        createDocumentation(reversedDate + " " + "Дубль-3", 3);
        createDocumentation(reversedDate + " " + "Дубль-4", 4);
    }

    public void handleGenerateButtonClick() {
        if (model.isDoublesGenerated((GregorianCalendar) dateOfMonday.clone())) {
            int answer = JOptionPane.showOptionDialog(
                    null,
                    "Дубли уже были сгенерированы ранее. Сгенерировать их ещё раз?",
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
        }

        List<PlanElement>[][] doubles = DoublesGenerator.generate(model, (GregorianCalendar) dateOfMonday.clone());
        DataDay[] dataDays = new DataDay[7];
        for (int i = 0; i < 7; i++) {
            List<PlanElement>[] oneDayDoubles = new ArrayList[4];
            for (int j = 0; j < 4; j++) {
                oneDayDoubles[j] = new ArrayList<>();
                oneDayDoubles[j].addAll(doubles[j][i]);
            }
            GregorianCalendar date = (GregorianCalendar) dateOfMonday.clone();
            date.add(Calendar.DAY_OF_MONTH, i);
            dataDays[i] = new DataDay(model.getDataDay(date).getPlanElementsDay(0), oneDayDoubles, date);
        }

        model.resetDataDays();

        model.addDataDays(dataDays);
        model.saveAllDataDays();

        GregorianCalendar date = (GregorianCalendar) dateOfMonday.clone();
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j <= 4; j++) {
                model.sortDataDay(date, j);
            }
            date.add(Calendar.DAY_OF_MONTH, 1);
        }

        updateDataInPlaylist();
    }

    public void handleMenuButtonClick() {
        model.resetDataDays();
        viewFinal.close();
        Starter.run(model);
    }
    
    private void createDocumentation(String name, int mode) {
        GregorianCalendar shiftedDate = (GregorianCalendar) dateOfMonday.clone();
        List<String> data = new ArrayList<>();

        data.add("ПРОГРАММА ПЕРЕДАЧ `РАДИО РОССИИ`");
        if (mode > 0) {
            data.add("РРД-" + mode);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YY");
        GregorianCalendar dateOfSunday = (GregorianCalendar) dateOfMonday.clone();
        dateOfSunday.add(Calendar.DAY_OF_MONTH, 6);
        data.add("на неделю с " + simpleDateFormat.format(dateOfMonday.getTime())
                + " по " + simpleDateFormat.format(dateOfSunday.getTime()));
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
        String outputPath = ProjectSettings.getParam(ProjectParams.OUTPUT_PATH);
        FileActions.createFile(outputPath + "\\" + name + ".txt", data);
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
