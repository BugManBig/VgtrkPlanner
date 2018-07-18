package com.company.finalFrame;

import com.company.DataDay;
import com.company.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        GregorianCalendar shiftedDate = new GregorianCalendar(date.get(Calendar.YEAR),
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
        
    }

    public void handleNextDayButtonClick() {
        
    }
    
    public void handlePrevDoubleButtonClick() {
        mode--;
        updateDataInList();
    }
    
    public void handleNextDoubleButtonClick() {
        mode++;
        updateDataInList();
    }
}
