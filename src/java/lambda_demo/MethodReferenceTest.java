package lambda_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * 方法引用 是 Java8 提供的新特性之一，能起到简化Lambda表达式的作用
 */
public class MethodReferenceTest {

  public static void main(String[] args) {
    Person[] personArray = new Person[3];
    personArray[0] = new Person("小明", 15);
    personArray[1] = new Person("小红", 18);
    personArray[2] = new Person("小兰", 7);

    // 原始方案，构造一个新类实现接口，再进行调用
    class PersonAgeComparator implements Comparator<Person> {

      public int compare(Person a, Person b) {
        return a.getAge().compareTo(b.getAge());
      }
    }
    Arrays.sort(personArray, new PersonAgeComparator());

    // 每次构建一个接口类的实现非常麻烦，可以使用匿名内部类替代
    Arrays.sort(personArray, new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        return a.getAge().compareTo(b.getAge());
      }
    });

    // 由于Comparator是一个函数式接口，可以使用 lambda 表达式进行替代，lambda 表达式能够省略构建匿名方法环节
    Arrays.sort(personArray, (a, b) -> a.getAge().compareTo(b.getAge()));

    // lambda 表达式仍然能够继续简化为 方法引用
    Arrays.sort(personArray, Comparator.comparing(Person::getAge));

    // -------------------- 引用类型的几种类型 --------------------
    System.out.println("类型 \t 语法 \t 对应的Lambda表达式");

    System.out.println("静态方法引用 \t 类名::staticMethod \t (args) -> 类名.staticMethod(args)");
    // 对一个Integer列表进行排序，因为Integer中已经存在静态的比较方法compare()，因此可以直接用静态方法引用的方式来调用
    List<Integer> list = Arrays.asList(82, 22, 34, 50, 9);
    list.sort(Integer::compare);
    System.out.println(list);

    /**
     * 实例方法引用，顾名思义就是调用已经存在的实例的方法，与静态方法引用不同的是类要先实例化，静态方法引用类无需实例化，直接用类名去调用。
     * */
    System.out.println("实例方法引用 \t inst::instMethod \t (args) -> inst.instMethod(args)");
    Person person = new Person("欧阳峰", 32);
    Supplier<String> supplier = () -> person.getName();
    System.out.println("Lambda表达式输出结果：" + supplier.get());

    Supplier<String> supplier2 = person::getName;
    System.out.println("实例方法引用输出结果：" + supplier2.get());

    /**
     * 若Lambda参数列表中的第一个参数是实例方法的参数调用者，而第二个参数是实例方法的参数时，可以使用对象方法引用。
     * */
    System.out.println("对象方法引用 \t 类名::instMethod \t (inst,args) -> 类名.instMethod(args)");

    BiPredicate<String, String> bp = (x, y) -> x.equals(y);
    boolean test = bp.test("xy", "xx");
    System.out.println(test);

    BiPredicate<String, String> bp1 = String::equals;
    boolean test1 = bp1.test("xy", "xx");
    System.out.println(test1);

    /**
     * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。
     * */
    System.out.println("构建方法引用 \t 类名::new \t (args) -> new 类名(args)");
    Supplier<List<Person>> personSupplier = () -> new ArrayList<>();
    List<Person> people = personSupplier.get();

    Supplier<List<Person>> personSupplier2 = ArrayList::new;    // 构造方法引用写法
    List<Person> people2 = personSupplier2.get();
  }

}

class Person {

  private String name;

  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public static int compareByAge(Person a, Person b) {
    return a.age.compareTo(b.age);
  }
}