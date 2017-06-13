package com.simonov.nikita.weatherapp.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Nikita Simonov
 */

public class DateUtil {

    private static final String DD_MMM_YYYY = "dd MMMM, hh:mm";

    public static String formatDate(long time) {
        Date date = new Date(toMills(time));
        Format formatter = new SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault());
        return formatter.format(date);
    }

    private static long toMills(long time) {
        return time * 1000;
    }
}
