package com.company.finalFrame;

import com.company.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerFinal {
    private ViewFinal viewFinal;
    private Model model;
    private int mode = 0;
    private int dayOfWeek = 0;
    private GregorianCalendar date;

    public void setViewFinal(ViewFinal viewFinal) {
        this.viewFinal = viewFinal;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }
    
    public void updateDataInList() {
        viewFinal.setWeekDayText(dayOfWeek);
        viewFinal.setDoubleText(mode);

        GregorianCalendar shiftedDate = new GregorianCalendar(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH));
        shiftedDate.add(Calendar.DAY_OF_MONTH, dayOfWeek);
        DataDay dataDay = model.getDataDay(shiftedDate);
        String[] data = new String[dataDay.getPlanElementsDay(mode).size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = dataDay.getPlanElementsDay(mode).get(i).getDataStringForFederal();
        }
        viewFinal.setDataToList(data);
    }

    public void handleEditButtonClick() {
        
    }

    public void handleAddButtonClick() {
        
    }

    public void handleRemoveButtonClick() {
        
    }

    public void handlePrevDayButtonClick() {
        dayOfWeek = dayOfWeek > 0 ? dayOfWeek - 1 : dayOfWeek;
        updateDataInList();
    }

    public void handleNextDayButtonClick() {
        dayOfWeek = dayOfWeek < 6 ? dayOfWeek + 1 : dayOfWeek;
        updateDataInList();
    }
    
    public void handlePrevDoubleButtonClick() {
        mode = mode > 0 ? mode - 1 : mode;
        updateDataInList();
    }
    
    public void handleNextDoubleButtonClick() {
        mode = mode < 4 ? mode + 1 : mode;
        updateDataInList();
    }
    
    public void handleDocumentationButtonClick() {
        GregorianCalendar shiftedDate = new GregorianCalendar(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH));
        List<String> data = new ArrayList<>();
        data.add("ПРОГРАММА ПЕРЕДАЧ `РАДИО РОССИИ`");
        data.add("на неделю с "
                + twoDigitsNumber(date.get(Calendar.DAY_OF_MONTH)) + "."
                + twoDigitsNumber((date.get(Calendar.MONTH) + 1)) + "."
                + String.valueOf(date.get(Calendar.YEAR)).substring(2));
        data.add("(время московское)");
        data.add("");
        data.add("");
        String line;
        List<PlanElement> list;
        for (int i = 0; i < 7; i++) {
            list = model.getDataDay(shiftedDate).getPlanElementsDay(0);
            data.add(shiftedDate.get(Calendar.DAY_OF_MONTH) + " "
                    + (getMonthName(shiftedDate.get(Calendar.MONTH))) + " - "
                    + DaysOfWeek.values()[i].toString());
            for (PlanElement planElement : list) {
                line = planElement.getStartTime().getTimeStringSmall();
                line += " " + planElement.getTitle();
                data.add(line);
            }
            data.add("");
            shiftedDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        FileActions.createFile("D:/AMyasnikov/Documentation.txt", data);
    }
    
    private String twoDigitsNumber(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }
    
    private String getMonthName(int monthNumber) {
        String[] monthNames = {
                "января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        return monthNames[monthNumber];
    }
}
