package i18n_demo;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleBoundleTest {

  public static void main(String[] args) {
    // 获得系统默认的国家/语言环境
    Locale myLocale = Locale.getDefault();
    // 根据指定的国家/语言环境加载资源文件
    ResourceBundle bundle = ResourceBundle.getBundle("mess", myLocale);
    // 打印从资源文件中取得的消息
    System.out.println(new String(bundle.getString("hello").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
  }

}
