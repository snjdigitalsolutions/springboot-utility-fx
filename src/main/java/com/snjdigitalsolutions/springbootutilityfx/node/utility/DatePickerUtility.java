package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DatePickerUtility {

    public void configureDatePicker(final DatePicker datePicker) {
        final String pattern = "yyyy-MM-dd";
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        datePicker.setPromptText(pattern.toUpperCase());
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return dateFormatter.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String s) {
                if (s != null && !s.isEmpty()) {
                    return LocalDate.parse(s, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

}
