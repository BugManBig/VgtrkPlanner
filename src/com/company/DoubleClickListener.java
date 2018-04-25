package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

public class DoubleClickListener implements MouseListener {
    private long prevClickTime = 0;
    private Point mousePrevClickLocation = null;

    private ActionListener listenerForEditButton;

    public DoubleClickListener(ActionListener listenerForEditButton) {
        this.listenerForEditButton = listenerForEditButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
        if (new GregorianCalendar().getTimeInMillis() - prevClickTime < 500
                && mousePrevClickLocation.x == currentPoint.x && mousePrevClickLocation.y == currentPoint.y) {
            listenerForEditButton.actionPerformed(null);
        }
        prevClickTime = new GregorianCalendar().getTimeInMillis();
        mousePrevClickLocation = currentPoint;
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
