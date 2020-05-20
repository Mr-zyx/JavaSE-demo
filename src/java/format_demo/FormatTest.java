package format_demo;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 主要介绍 Format 类中的几个子类的用法
 */
public class FormatTest {

  public static void main(String[] args) {
    /**
     * MessageFormat 能够通过占位符动态替换字符串中的元素
     * */
    String msg = "你好, {0}! 今天是{1}";
    System.out.println(MessageFormat.format(msg, "小明", new Date()));
    String msg1 = "你好, {0}! 今天是{1}, {0}强无敌";
    System.out.println(MessageFormat.format(msg1, "小明", new Date()));

    System.out.println("--------------------------");

    /**
     * NumberFormat 能将数值转化为字符串，也可以将字符串转化为数值。它是一个抽象类，无法通过构造器创建对象，但提供了一些类方法来实例化对象
     *
     * getCurrencyInstance(): 返回默认Locale 的货币格式器。也可以在调用时传入指定的Locale，则获取指定 Locale 的货币格式器
     * getIntegerInstance(): 返回默认 Locale 的整数格式器。也可以在调用该方法时传入指定的 Locale，则获取指定 Locale 的整数格式器。
     * getNumberInstance(): 返回默认 Locale 的通用格式器。也可以在调用该方法时传入指定的 Locale，则获取指定 Locale 的通用格式器。
     * getPercentInstance(): 返回默认 Locale 的百分数格式器。也可以在调用该方法时传入指定的 Locale，则获取指定 Locale 的百分数格式器。
     * */
    double d = 1234000.567;
    // 创建 4 个 Locale，分别代表中国、日本、德国、美国
    Locale[] locales = {Locale.CHINA, Locale.JAPAN, Locale.GERMAN, Locale.US};
    NumberFormat[] nf = new NumberFormat[12];
    // 为上面 4 个 Locale 创建 12 个 NumberFormat 对象，每个 Locale 都有多种格式器
    for (int i = 0; i < locales.length; i++) {
      nf[i * 3] = NumberFormat.getNumberInstance(locales[i]);
      nf[i * 3 + 1] = NumberFormat.getPercentInstance(locales[i]);
      nf[i * 3 + 2] = NumberFormat.getCurrencyInstance(locales[i]);
    }

    for (int i = 0; i < locales.length; i++) {
      String tip = i == 0 ? "----中国的格式----" : i == 1 ? "----日本的格式----" : i == 2 ? "----德国的格式----" : "----美国的格式----";
      System.out.println(tip);
      System.out.println("通用数值格式：" + nf[i * 3].format(d));
      System.out.println("百分比数值格式：" + nf[i * 3 + 1].format(d));
      System.out.println("货币数值格式：" + nf[i * 3 + 2].format(d));
    }

  }
}
