package com.binghu.risk.utils.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    //时间字符串的格式
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(PATTERN);
    }

    public static String convertLocalDateTime2Str(LocalDateTime dateTime){
        DateTimeFormatter dtf =  getFormatter();
        return dtf.format(dateTime);
    }


    public static LocalDateTime convertStr2LocalDateTime(String str) {
        DateTimeFormatter dtf =  getFormatter();
        return LocalDateTime.parse(str,dtf);
    }


    public static LocalDateTime convertTimestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static long convertLocalDateTime2Timestamp(LocalDateTime dateTime) {
        return  dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static LocalDateTime localDateTimePlusDays(LocalDateTime dateTime,String days) {
        return dateTime.plusDays(Long.parseLong(days));
    }

    public static LocalDateTime localDateTimePlusSec(LocalDateTime dateTime, String second) {
        return dateTime.plusSeconds(Long.parseLong(second));
    }
}
