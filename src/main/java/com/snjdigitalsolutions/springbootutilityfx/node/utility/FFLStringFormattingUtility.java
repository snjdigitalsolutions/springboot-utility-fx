package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import org.springframework.stereotype.Component;

@Component
public class FFLStringFormattingUtility {

    public String formatString(String fflString) {
        if (fflString.length() == 1) {
            fflString = fflString + "-";
        } else if (fflString.length() == 4) {
            fflString = fflString + "-";
        } else if (fflString.length() == 8) {
            fflString = fflString + "-";
        } else if (fflString.length() == 11) {
            fflString = fflString + "-";
        } else if (fflString.length() == 14) {
            fflString = fflString + "-";
        } else if (fflString.length() > 20) {
            fflString = fflString.substring(0, 20);
        }
        return fflString;
    }

    public String removeHyphens(String fflString) {
        return fflString.replaceAll("-", "");
    }
}
