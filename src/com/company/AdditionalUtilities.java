package com.company;

public class AdditionalUtilities {
    public static String getStandardTime(SoundElement soundElement, boolean isMainTime) {
        if (isMainTime) {
            return getTwoDigitString(soundElement.get(true, TimeNames.HOURS)) + ":"
                    + getTwoDigitString(soundElement.get(true, TimeNames.MINUTES))
                    + ":" + getTwoDigitString(soundElement.get(true, TimeNames.SECONDS));
        }
        return getTwoDigitString(soundElement.get(false, TimeNames.HOURS)) + ":"
                + getTwoDigitString(soundElement.get(false, TimeNames.MINUTES))
                + ":" + getTwoDigitString(soundElement.get(false, TimeNames.SECONDS));
    }

    private static String getTwoDigitString(int number) {
        if (number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }
}
