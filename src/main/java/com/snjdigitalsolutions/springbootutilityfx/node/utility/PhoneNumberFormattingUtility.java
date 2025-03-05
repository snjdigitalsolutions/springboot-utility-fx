package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberFormattingUtility {

    public boolean isFormattable(String phoneNumber) {
        boolean isFormattable = false;
        phoneNumber = phoneNumber.replaceAll("\\D", "");
        if (phoneNumber.length() == 10) {
            isFormattable = true;
        }
        return isFormattable;
    }

    public String format(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\D", "");
        if (phoneNumber.length() == 10) {
            String firstThreeDigits = phoneNumber.substring(0, 3);
            String secondThreeDigits = phoneNumber.substring(3, 6);
            String lastFourDigits = phoneNumber.substring(6, 10);
            phoneNumber = "(" + firstThreeDigits + ") " + secondThreeDigits + "-" + lastFourDigits;
        }
        return phoneNumber;
    }

}
