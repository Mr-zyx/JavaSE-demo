package time_demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {

  private static final String[] DATE_NAME_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
      "Saturday"};

  public static Date toDate(ZonedDateTime zonedDateTime) {
    return Date.from(zonedDateTime.toInstant());
  }

  public static ZonedDateTime toZonedDateTime(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault());
  }

  public static ZonedDateTime toZonedDateTime(Date date, String zone) {
    ZonedDateTime originalZdt = toZonedDateTime(date);
    ZoneId zoneId = ZoneId.of(zone);
    return originalZdt.withZoneSameInstant(zoneId);
  }

  public static Date toDate(LocalDateTime localDateTime) {
    return toDate(localDateTime.atZone(ZoneId.systemDefault()));
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    return toZonedDateTime(date).toLocalDateTime();
  }

  public static Date getDaysAfterTheDay(Date date, int daysAfter) {
    return new Date(date.getTime() + TimeUnit.DAYS.toMillis(daysAfter));
  }

  public static Date getDaysBeforeNow(int daysBefore) {
    return new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(daysBefore));
  }

  public static long getDaysBetween(Date date1, Date date2) {
    long diff = date2.getTime() - date1.getTime();
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }

  public static LocalDateTime getStartOfDay(Date date, String zone) {
    // date -> zoned
    final ZoneId zoneId = ZoneId.of(zone);
    final ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
    final ZonedDateTime startOfDay = zonedDateTime.toLocalDate().atStartOfDay(zoneId);
    // zoned -> date
    return startOfDay.toLocalDateTime();
  }

  public static String getDayNameOfTheWeek(Date date, String timeZoneName) {
    Calendar c = Calendar.getInstance();
    if (timeZoneName != null) {
      c.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZoneName)));
    }
    c.setTime(date);
    return DATE_NAME_OF_WEEK[c.get(Calendar.DAY_OF_WEEK) - 1];
  }

}
