package regex_demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式相关代码示例
 */
public class RegexTest {

  public static void main(String[] args) {
    String str = "hello, java!";
    // 贪婪模式的正则表达式 （默认使用贪婪模式，贪婪模式的表达式会一直匹配下去，直到无法匹配为止）
    System.out.println(str.replaceFirst("\\w*", "-")); // 输出 -, java!
    // 勉强模式的正则表达式（用问号后缀表示，它只会匹配最少字符）
    System.out.println(str.replaceFirst("\\w*?", "-")); // 输出 -hello, java!   因为 * 匹配最少字符为0个字符

    System.out.println("----------------------------------");
    /**
     * 一旦在程序中定义了正则表达式，就可以使用 Pattern 和 Matcher来使用正则表达式。
     *
     * Pattern 对象是正则表达式编译后在内存中的表示形式，因此，正则表达式字符串必须先被编译为 Pattern 对象，
     * 然后再利用该 Pattern 对象创建对应的 Matcher 对象。执行匹配所涉及的状态保留在 Matcher 对象中，
     * 多个 Matcher 对象可共享同一个 Pattern 对象
     * */
    // 讲一个字符串编译成 Pattern 对象
    Pattern p = Pattern.compile("a*b");
    // 使用 Pattern 对象创建 Matcher 对象
    Matcher m = p.matcher("aaaaab");
    boolean b = m.matches();
    System.out.println("b = " + b); // true
    /*
     * 上面定义的 Pattern 对象可以多次重复使用。如果某个正则表达式仅需一次使用，则可直接使用 Pattern 类的静态 matches() 方法，
     * 此方法自动把指定字符串编译成匿名的 Pattern 对象，并执行匹配。
     * 但由于这种语句每次都需要重新编译新的 Pattern 对象，不能重复利用已编译的 Pattern 对象， 所以效率不高。
     * */
    boolean b2 = Pattern.matches("a*b", "aaaaab");
    System.out.println("b2 = " + b2); // true

    System.out.println("----------------------------------");
    /**
     * 通过 Matcher 类的 find() 和 group() 方法可以从目标字符串中依次取出特定字串（匹配正则表达式的子串
     * */
    // 使用字符串模拟从网上得到的网页源码
    String str2 = "我想求购一本《三国演义》，尽快联系我13586034579"
        + "交朋友，电话号码是15635074928"
        + "出售二手电脑，联系方式13607113329";
    // 创建一个 Pattern 对象，并用它建立一个 Matcher 对象。该正则表达式只抓取 13 和 15 开头的电话号码
    Matcher matcher = Pattern.compile("(13|15)\\d{9}").matcher(str2);
    // 将所有符合正则表达式的子串全部输出
    while (matcher.find()) { // find() 依次查找字符串中与 Pattern 匹配的子串，一旦找到，下次调用时将接着向下查找
      System.out.println(matcher.group());
    }

    System.out.println("----------------------------------");
    /**
     * find() 方法还可以传入一个 int 类型的参数，带 int 参数的 find() 方法将从该 int 索引处向下搜索。
     * start() 和 end() 方法主要用于确定子串在目标字符串中的位置
     * */
    String regStr = "Java is very easy!";
    Matcher m2 = Pattern.compile("\\w+").matcher(regStr);
    while (m2.find()) {
      System.out.println(m2.group() + "子串的起始位置: " + m2.start() + ", 其结束位置: " + m2.end());
    }

    System.out.println("----------------------------------");
    /**
     * matches() 和 lookingAt() 方法有点相似，只是 matches() 方法要求整个字符串和 Pattern 完全匹配时才返回 true,
     * 而 lookingAt() 只要字符串以 Pattern 开头就会返回 true。reset() 方法可将现有的 Matcher 对象应用于新的字符序列
     * */
    String[] mails = {
        "shawnZheng@gmail.com",
        "mr-zyx@qq.com",
        "yuyuyu123@hotmail.cn",
        "abc123@aa.xx"
    };
    String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
    Pattern mailPattern = Pattern.compile(mailRegEx);
    Matcher mailMatcher = null;
    for (String mail : mails) {
      if (mailMatcher == null) {
        mailMatcher = mailPattern.matcher(mail);
      } else {
        mailMatcher.reset(mail);
      }
      String result = mail + (mailMatcher.matches() ? "是" : "不是") + "一个有效的邮件地址!";
      System.out.println(result);
    }
  }

}
