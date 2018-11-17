package com.company;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateInput {
    public static GregorianCalendar run() {
        String source = JOptionPane.showInputDialog(null, "Введите дату понедельника в формате ДД.ММ.ГГ",
                "Введите дату", JOptionPane.QUESTION_MESSAGE);
        if (source == null) {
            return null;
        }
        if (source.length() != 8) {
            JOptionPane.showMessageDialog(null, "Неверный формат", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int day = Integer.parseInt(source.substring(0, 2));
        int month = Integer.parseInt(source.substring(3, 5)) - 1;
        int year = Integer.parseInt(source.substring(6, 8)) + 2000;
        GregorianCalendar mondayDate = new GregorianCalendar(year, month, day);
        if (mondayDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            JOptionPane.showMessageDialog(null, "Введённая дата не является понедельником", "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return mondayDate;
    }
}
