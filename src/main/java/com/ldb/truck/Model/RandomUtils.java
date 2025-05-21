package com.ldb.truck.Model;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author Asus
 */
public class RandomUtils {
    public static String getRandomUnique(String heading) {
        // Get the current day of the year
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        Random r = new Random(System.currentTimeMillis());

        String number = heading + String.format("%ty", Year.now()) + String.format("%03d", dayOfYear) + (r.nextInt(1000)) + (r.nextInt(1000));
        return String.valueOf(number);
    }

}