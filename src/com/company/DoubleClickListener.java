package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

public class DoubleClickListener implements MouseListener {
    private long prevTime = 0;
    private Point prevPosition;
    
    private ActionListener actionListener;

    public DoubleClickListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        long time = new GregorianCalendar().getTimeInMillis();
        Point position = MouseInfo.getPointerInfo().getLocation();
        if (time - prevTime < 500 && position.x == prevPosition.x && position.y == prevPosition.y) {
            actionListener.actionPerformed(null);
        }
        prevPosition = position;
        prevTime = time;
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
