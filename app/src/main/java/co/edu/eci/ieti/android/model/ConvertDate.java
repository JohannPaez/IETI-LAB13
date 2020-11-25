package co.edu.eci.ieti.android.model;

import androidx.room.TypeConverter;

import java.util.Date;

public class ConvertDate {
    @TypeConverter
    public static Date longToDate(Long timestamp) {
        if (timestamp == null) return null;
        return new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToLong(Date date) {
        if (date == null) return null;
        return date.getTime();
    }
}
