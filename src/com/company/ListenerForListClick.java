package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerForListClick implements MouseListener {
    private FedTimetable fedTimetable;

    public ListenerForListClick(FedTimetable fedTimetable) {
        this.fedTimetable = fedTimetable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        fedTimetable.checkEnablingButtons();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
