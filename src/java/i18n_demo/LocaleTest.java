package i18n_demo;

import java.util.Locale;

public class LocaleTest {

  public static void main(String[] args) {
    // 返回 Java 所支持的全部国家和语言的数组
    Locale[] localeList = Locale.getAvailableLocales();
    // 遍历数组的每一个元素，依次获取所支持的国家和语言
    for (Locale locale : localeList) {
      // 输出所支持的国家和语言
      System.out.println(locale.getDisplayCountry() + "=" + locale.getCountry() + " " + locale
          .getDisplayLanguage() + "=" + locale.getLanguage());
    }
  }

}
