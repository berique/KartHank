package br.com.cubotecnologia.kartrank;

import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.BeforeClass;

import java.lang.reflect.Field;

/**
 * Created by henriquemoreno on 22/03/17.
 */
public class BaseKartRankTest {

    @BeforeClass
    public static void beforeClass() throws NoSuchFieldException, IllegalAccessException {
        final Field f = ISOPeriodFormat.class.getDeclaredField("cStandard");
        f.setAccessible(true);
        f.set(null, new PeriodFormatterBuilder()
                .appendMonths()
                .appendSuffix(" month", " months")
                .appendWeeks()
                .appendSuffix(" week", " weeks")
                .appendDays()
                .appendSuffix(" day", " days")
                .appendHours()
                .appendSuffix(" hour", " hours")
                .appendMinutes()
                .appendSuffix("minute", "minutes")
                .appendSecondsWithOptionalMillis()
                .appendSuffix("second", "seconds")
                .toFormatter());
    }
}
