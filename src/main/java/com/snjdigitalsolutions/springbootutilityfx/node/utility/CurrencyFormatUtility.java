package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class CurrencyFormatUtility {

    private static final DecimalFormat formatter = new DecimalFormat("###,###.00");

    public static String format(double value) {
        return formatter.format(value);
    }

}
