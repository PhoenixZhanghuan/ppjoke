package com.example.libnetwork.cache;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * @author zhanghuan
 */
public class DateConverter {

    @TypeConverter
    public static Long date2Long(Date date) {
        return date.getTime();
    }

    public static Date long2Date(Long data) {
        return new Date(data);
    }
}
