package com.company.federalFrame;

public class ExtraHandlersImpl extends ControllerFederalFrame implements ExtraHandlers {
    @Override
    public void handleNextButtonClick() {
        weekday++;
        updateDataInPlaylist();
    }

    @Override
    public void handlePrevButtonClick() {
        weekday--;
        updateDataInPlaylist();
    }
}
