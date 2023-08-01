package com.fubuki.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChangeLocalDateTime {
    public static Date changeToDate(LocalDateTime ldt) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = ldt.format(dateTimeFormatter1);
        return Date.from(LocalDateTime.parse(date, dateTimeFormatter1).
                atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String changeToString(LocalDateTime ldt) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(dateTimeFormatter1);
    }
}
