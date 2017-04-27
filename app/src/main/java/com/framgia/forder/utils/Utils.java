package com.framgia.forder.utils;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Duong on 4/14/2017.
 */

public class Utils {
    public static final String INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String OUTPUT_TIME_FORMAT = "HH:mm";
    public static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String FORMAT_PRICE = "%1$,.0f";

    public static class DateTimeUntils {
        public static String convertUiFormatToDataFormat(String time, String inputFormat,
                String outputFormat) {
            if (TextUtils.isEmpty(time)) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
            SimpleDateFormat newSdf = new SimpleDateFormat(outputFormat, Locale.getDefault());
            try {
                return newSdf.format(sdf.parse(time));
            } catch (ParseException e) {
                return null;
            }
        }

        public static boolean isProductTimeOut(String start, String end) {
            SimpleDateFormat parser = new SimpleDateFormat(Constant.FORMAT_TIME);
            Date currentHour = convertStringToDate(parser.format(new Date()));
            Date startHour = convertStringToDate(start);
            Date endHour = convertStringToDate(end);
            return currentHour.before(startHour) && currentHour.after(endHour);
        }

        private static Date convertStringToDate(String date) {
            SimpleDateFormat parser = new SimpleDateFormat(Constant.FORMAT_TIME);
            try {
                return parser.parse(date);
            } catch (java.text.ParseException e) {
                return new Date();
            }
        }
    }
}
