package time_demo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date format 相关示例
 */
public class DateFormatTest {

  public static void main(String[] args) {
    // -------------------- 日期时间格式化 --------------------
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdf.format(new Date());
    System.out.println(dateStr); // e.g. 2020-05-07 22:43:46

    String dateStr2 = DateTimeFormatter.ofPattern("现在是yyyy-MM-dd HH:mm:ss 是今年的第D天").format(ZonedDateTime.now());
    System.out.println(dateStr2);

    // -------------------- 日期时间解析 --------------------
    LocalDateTime local = LocalDateTime
        .parse("2020-05-07 22:58:26", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Date date = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());//转换成Date
    System.out.println(date); // Thu May 07 22:58:26 CST 2020
  }

}
