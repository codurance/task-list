package com.codurance.training.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Helper {
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}
