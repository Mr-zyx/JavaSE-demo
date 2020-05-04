package time_demo;

import java.util.Calendar;
import java.util.Date;

/**
 * Java8 涵盖的时间、日期知识点
 */
public class DateTimeTest {

  public static void main(String[] args) {

    /**
     * java.util.Date类有两个未过期的构造函数
     * */
    // 无参构造函数底层使用System.currentTimeMillis()获得long整数作为日期参数
    Date d1 = new Date();

    // 获取当前时间之后100毫秒的时间
    Date d2 = new Date(System.currentTimeMillis() + 100);
    System.out.println(d2);
    System.out.println(d1.compareTo(d2)); // -1 (d1在d2之前)
    System.out.println(d1.before(d2)); // true
    System.out.println(d1.after(d2)); // false

    System.out.println("------------------------------------------------------------------------------------");

    /**
     * Calendar类是抽象类，不能用构造函数实例化
     * */
    Calendar calendar = Calendar.getInstance();
    // 从Calendar对象中取出Date对象
    Date date = calendar.getTime();
    System.out.println("当前时间：" + date);
    // 从Date对象中获得对应的Calendar对象。
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTime(date);

    Calendar c = Calendar.getInstance();
    System.out.println(c.getActualMaximum(Calendar.MONTH)); // 返回月份最大能拥有的值 11
    System.out.println(c.getActualMaximum(Calendar.DATE)); // 返回日期最大能拥有的值 31
    System.out.println(c.getActualMinimum(Calendar.DATE)); // 返回日期最小能拥有的值 1

    System.out.println(c.get(Calendar.YEAR));
    System.out.println(c.get(Calendar.MONTH));
    System.out.println(c.get(Calendar.DATE));
    System.out.println(c.get(Calendar.DAY_OF_YEAR));
    System.out.println(c.get(Calendar.ZONE_OFFSET));
    System.out.println(c.get(Calendar.DST_OFFSET));
    c.set(2003, Calendar.NOVEMBER, 23, 12, 32, 23); // 2003-11-23 12:32:23
    System.out.println(c.getTime());
    c.add(Calendar.YEAR, -1); // 2002-11-23 12:32:23
    System.out.println(c.getTime());
    c.set(2015, Calendar.JANUARY, 27); // 只设置年月日 2015-01-27 12:32:23
    System.out.println(c.getTime());

    System.out.println("----------------------------------");
    // add() & roll()的区别：
    // add(): 当被修改的字段超出它的允许范围时，会发生进位，即上一级字段也会增大
    Calendar cal1 = Calendar.getInstance();
    cal1.set(2003, Calendar.AUGUST, 23, 0, 0, 0); // 2003-08-23
    cal1.add(Calendar.MONTH, 6); // 2003-08-23 => 2004-02-23 (年份进位)
    System.out.println(cal1.getTime());
    // 如果下一级字段也需要改变，那么该字段会修正到变化最小的值。如：
    Calendar cal2 = Calendar.getInstance();
    cal2.set(2003, Calendar.AUGUST, 31, 0, 0, 0); // 2003-08-31
    cal2.add(Calendar.MONTH, 6); // 2003-08-31 => 2004-02-29 (进位后的2月没有31日，最大29日)
    System.out.println(cal2.getTime());

    // roll(): 当被修改的字段超出它的允许范围时，上一级字段不会增大
    Calendar cal3 = Calendar.getInstance();
    cal3.set(2003, Calendar.AUGUST, 23, 0, 0, 0); // 2003-08-23
    cal3.roll(Calendar.MONTH, 6); //  2003-08-23 => 2003-02-23 (年份不进位)
    System.out.println(cal3.getTime());
    // 下一级字段处理与add()类似，也会修正到变化最小的值
    Calendar cal4 = Calendar.getInstance();
    cal4.set(2003, Calendar.AUGUST, 31, 0, 0, 0); // 2003-08-31
    cal4.roll(Calendar.MONTH, 6); // 2003-08-31 => 2003-02-28
    System.out.println(cal4.getTime());

    System.out.println("----------------------------------");
    // Calendar的容错性
    Calendar cal5 = Calendar.getInstance();
    System.out.println(cal5.getTime());
    cal5.set(Calendar.MONTH, 13); // month最大11，13是不合法的值，但由于容错性，它会进位
    System.out.println(cal5.getTime());

    Calendar cal6 = Calendar.getInstance();
    cal6.setLenient(false);  // 关闭容错性
    // cal6.set(Calendar.MONTH, 13); // month最大11，13是不合法的值，容错性关闭会抛出 IllegalArgumentException

    System.out.println("----------------------------------");
    // set()方法延时修改
    Calendar cal7 = Calendar.getInstance();
    cal7.set(2003, Calendar.AUGUST, 31);
    // 将月份改为9，但9月31日不存在，如果立即修改，系统会自动调整到10月1日
    cal7.set(Calendar.MONTH, Calendar.SEPTEMBER);
    System.out.println(cal7.getTime());
    Calendar cal8 = Calendar.getInstance();
    cal8.set(2003, Calendar.JANUARY, 31);
    // 将月份改为2，但2月31日不存在，系统会自动调整到3月3日
    cal8.set(Calendar.MONTH, Calendar.FEBRUARY);
    System.out.println(cal8.getTime());

    // set()会延迟修改，直到get(), getTime(), getTimeInMills(), add(), roll()时才会重新计算日历的时间
    Calendar cal9 = Calendar.getInstance();
    cal9.set(2003, Calendar.AUGUST, 31); // 2003-08-31
    cal9.set(Calendar.MONTH, Calendar.SEPTEMBER);
    cal9.set(Calendar.DATE, 5);
    System.out.println(cal9.getTime());  // 2003-09-05
  }
}
