package com.sunil.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addDays(Date originDate, int addDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(originDate);
        c.add(Calendar.DATE, addDays);
        return c.getTime();
    };
}
