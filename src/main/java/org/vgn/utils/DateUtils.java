package org.vgn.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static long getDifferenceBetweenDatesByTimeUnit(LocalDateTime startDateTime, LocalDateTime endDateTime,
            ChronoUnit timeUnit) {

        switch (timeUnit) {
            case YEARS:
                return ChronoUnit.YEARS.between(startDateTime, endDateTime);

            case MONTHS:
                return ChronoUnit.MONTHS.between(startDateTime, endDateTime);

            case DAYS:
                return ChronoUnit.DAYS.between(startDateTime, endDateTime);

            case MINUTES:
                return ChronoUnit.MINUTES.between(startDateTime, endDateTime);

            default:
                throw new IllegalArgumentException("Unsupported time unit: " + timeUnit);

        }

    }

}
