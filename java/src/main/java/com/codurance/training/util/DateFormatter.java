package com.codurance.training.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String parseDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}
