package ir.ac.kntu.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public final class Calendar {
    public static final int TIME_SPEED = 6000;

    private static Instant start = Instant.now();

    private Calendar() {
    }

    public static Instant now() {
        return Instant.ofEpochMilli(start.toEpochMilli() + (Instant.now().toEpochMilli() - start.toEpochMilli()) * TIME_SPEED);
    }

    public static String getStringNow() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String dateNow = simpleDateFormat.format(date);
        return dateNow;
    }
}