package co.edu.uco.taskpeak.crosscutting.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper.getObjectHelper;

public final class DateHelper {

    public static final LocalDateTime DEFAULT = LocalDateTime.of(2023, 1, 1, 0, 0);
    public static final LocalDateTime NOW = LocalDateTime.now();

    public  static  final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateHelper() {
        super();
    }

    public static final boolean isNull(final LocalDateTime date) {
        return getObjectHelper().isNull(date);
    }

    public static final LocalDateTime getDefault(final LocalDateTime date, final LocalDateTime defaultValue) {
        return getObjectHelper().getDefault(date, defaultValue);
    }

    public static final LocalDateTime getDefault(final LocalDateTime date) {
        return getDefault(date, LocalDateTime.now());
    }

    public static final String format(final LocalDateTime date, final String pattern) {
        if (isNull(date)) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static final long daysBetween(final LocalDateTime startDate, final LocalDateTime endDate) {
        if (isNull(startDate) || isNull(endDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static final long hoursBetween(final LocalDateTime startDate, final LocalDateTime endDate) {
        if (isNull(startDate) || isNull(endDate)) {
            return 0;
        }
        return ChronoUnit.HOURS.between(startDate, endDate);
    }

    public static final long minutesBetween(final LocalDateTime startDate, final LocalDateTime endDate) {
        if (isNull(startDate) || isNull(endDate)) {
            return 0;
        }
        return ChronoUnit.MINUTES.between(startDate, endDate);
    }

    public static final boolean isBefore(final LocalDateTime date1, final LocalDateTime date2) {
        if (isNull(date1) || isNull(date2)) {
            return false;
        }
        return date1.isBefore(date2);
    }

    public static final boolean isAfter(final LocalDateTime date1, final LocalDateTime date2) {
        if (isNull(date1) || isNull(date2)) {
            return false;
        }
        return date1.isAfter(date2);
    }

    public static final LocalDateTime addDays(final LocalDateTime date, final long days) {
        if (isNull(date)) {
            return LocalDateTime.now();
        }
        return date.plusDays(days);
    }

    public static final LocalDateTime subtractDays(final LocalDateTime date, final long days) {
        if (isNull(date)) {
            return LocalDateTime.now();
        }
        return date.minusDays(days);
    }
}
