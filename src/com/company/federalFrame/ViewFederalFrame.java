package com.company.federalFrame;

import com.company.setkaFrame.ViewSetkaFrame;

import javax.swing.*;

public class ViewFederalFrame extends ViewSetkaFrame {
    private ExtraHandlers extraHandlers;

    public void setExtraHandlers(ExtraHandlers extraHandlers) {
        this.extraHandlers = extraHandlers;
    }

    public void extra() {
        JButton prevButton = new JButton("Prev");
        prevButton.setBounds(FRAME_WIDTH - 400, FRAME_HEIGHT - 80, 100, 30);
        prevButton.addActionListener(e -> extraHandlers.handlePrevButtonClick());
        frame.add(prevButton);
        
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(FRAME_WIDTH - 250, FRAME_HEIGHT - 80, 100, 30);
        nextButton.addActionListener(e -> extraHandlers.handleNextButtonClick());
        frame.add(nextButton);
        
        frame.repaint();
        frame.revalidate();
    }
}
