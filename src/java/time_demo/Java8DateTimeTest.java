package time_demo;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

/**
 * Java 8新增了java.time包，包含了一些常用类
 */
public class Java8DateTimeTest {

  public static void main(String[] args) {
    /**
     * Clock：该类用于获取指定时区的当前日期、时间。该类可以取代 System 类的 currentTimeMillis() 方法，
     * 而且提供类更多方法来获取当前日期、时间。该类提供了大量静态方法来获取Clock对象
     * */
    // 获取当前Clock
    Clock clock = Clock.systemUTC();
    // 通过Clock获取当前时刻（UTC时间）
    System.out.println("当前时刻为：" + clock.instant());
    // 获取clock对应的毫秒数，与System.currentTimeMillis()输出相同
    System.out.println(clock.millis());
    System.out.println(System.currentTimeMillis());

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * Duration：该类代表持续时间。该类可以非常方便的获取一段时间
     * */
    Duration d = Duration.ofSeconds(6000);
    System.out.println("6000秒相当于" + d.toMinutes() + "分");
    System.out.println("6000秒相当于" + d.toHours() + "小时");
    System.out.println("6000秒相当于" + d.toDays() + "天");
    // 在clock基础上增加6000秒，返回新的clock
    Clock clock2 = Clock.offset(clock, d);
    System.out.println("当前时刻加6000秒为：" + clock2.instant()); // 可以看到clock2与clock相差 1hr40min

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * Instant：代表一个具体时刻，可以精确到纳秒。该类提供了一个静态的 now() 方法来获取当前时刻，
     * 也提供了静态的 now(Clock clock) 方法来获取 clock 对应的时刻。
     * 除此之外，它还提供了一系列 minusXxx() 方法在当前时刻基础上减去一段时间，也提供了 plusXxx() 方法在当前时刻基础上加上一段时间
     * */
    // 获取当前时间
    Instant instant = Instant.now();
    System.out.println(instant);
    // instant 添加 6000 秒（即 100 分钟），返回新的instant
    Instant instant2 = instant.plusSeconds(6000);
    System.out.println(instant2);
    // 根据字符串解析 Instant 对象
    Instant instant3 = Instant.parse("2014-02-23T10:12:35.342Z");
    System.out.println(instant3);
    // 在 instant3 的基础上添加 5 小时 4 分钟
    Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
    System.out.println(instant4);
    // 获取 instant4 的 5 天以前的时刻
    Instant instant5 = instant4.minus(Duration.ofDays(5));
    System.out.println(instant5);

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * LocalDate：该类代表不带时区的日期，例如 2007-12-03。该类提供了静态的 now() 方法来获取当前日期，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的日期。
     * 除此之外，它还提供了 minusXxx() 方法在当前年份基础上减去几年、几月、几周或几日等。也提供了 plusXxx() 方法在当前年份基础上加几年、几月、几周或几日等
     * */
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate);
    // 获得 2014 年的第 146 天
    localDate = LocalDate.ofYearDay(2014, 146);
    System.out.println(localDate); // 2014-05-26
    // 设置为 2014 年 5 月 21 日
    localDate = LocalDate.of(2014, Month.MAY, 21);
    System.out.println(localDate); // 2014-05-21

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * LocalTime：该类代表不带时区的时间，例如 10：15：30。该类提供了静态的 now() 方法来获取当前时间，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的时间。
     * 除此之外，它还提供了 minusXxx() 方法在当前年份基础上减去几小时、几分、几秒等。也提供了 plusXxx() 方法在当前年份基础上加几小时、几分、几秒等
     * */
    // 获取当前时间
    LocalTime localTime = LocalTime.now();
    // 设置为 23 点 33 分
    localTime = LocalTime.of(22, 33);
    System.out.println(localTime); // 22:33
    // 返回一天中的第 5503 秒
    localTime = LocalTime.ofSecondOfDay(5503);
    System.out.println(localTime); // 01:31:43

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * LocalDateTime：该类代表不带时区的日期、时间，例如 2018-12-03T10：15：30。该类提供了静态的now() 方法来获取当前日期、时间，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的日期、时间。
     * 除此之外，它还提供了 minusXxx() 方法在当前年份的基础上减去几年、几月、几日、几小时、几分、几秒等，也提供了 plusXxx() 方法在当前年份基础上加上几年、几月、几日、几小时、几分、几秒等
     * */
    // 获取当前日期、时间
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    // 当前日期、时间加上 25 小时 3 分钟
    LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);
    System.out.println("当前日期、时间的 25 小时 3 分之后：" + future);

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * Year：该类仅代表年，例如 2014。该类提供了静态的 now() 方法来获取当前年份，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的年份。
     *  除此之外，它还提供了 minusYears() 方法在当前年份的基础上减去几年，也提供了 plusYears() 方法在当前年份的基础上加上几年
     * */
    Year year = Year.now();
    System.out.println("当前年份：" + year);
    year = year.plusYears(5); // 当前年份再加 5 年
    System.out.println("当前年份再过 5 年：" + year);

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * YearMonth：该类仅代表年月，例如 2014-05。该类提供了静态的 now() 方法来获取当前年月，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的年月。
     * 除此之外，它还提供了 minusXxx() 方法在当前年份的基础上减去几年、几月，也提供了 plusXxx() 方法在当前年份的基础上加上几年、几月
     * */
    YearMonth ym = YearMonth.now();
    System.out.println("当前年月：" + ym);

    ym = year.atMonth(10);
    System.out.println("Year 年 10 月：" + ym);
    // 当前年月再加 5 年、减 3 个月
    ym = ym.plusYears(5).minusMonths(3);
    System.out.println("year 年 10 月再加 5 年、减 3 个月：" + ym);

    System.out.println("---------------------------------------------------------------------------------");

    /**
     * MonthDay：该类仅代表年月，例如 --04-15。该类提供了静态的 now() 方法来获取当前月日，也提供了静态的 now(Clock clock) 方法来获取 clock 对应的月日。
     * */
    MonthDay monthDay = MonthDay.now();
    System.out.println("当前月日：" + monthDay); //输出 --XX-XX，代表几月几日
    // 设置为 5 月 23 日
    MonthDay monthDay2 = monthDay.with(Month.MAY).withDayOfMonth(23);
    System.out.println("5 月 23 日为：" + monthDay2);
  }
}
