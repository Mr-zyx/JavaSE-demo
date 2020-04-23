package extend_demo;

/**
 * <h1>初始化块相关demo</h1>
 * <p>
 * 静态初始化块最先执行，但只执行一次（类初始化成功后，该类会一直存在于JVM中，无需再次对类初始化）。
 * <p>
 * 初始化块优先于构造方法执行。通常把多个构造方法的共同部分能提出来作为初始化块，但初始化块不能接收任何参数。在编译阶段，初始化块实际上被"还原"到了所有的构造方法最前部。
 */
public class InitializationBlockTest {

  public static void main(String[] args) {
    new Leaf();
    System.out.println("--------------------------");
    new Leaf();
  }
}

class Root {

  static {
    System.out.println("Root 的静态初始化快");
  }

  {
    System.out.println("Root 的普通初始化快");
  }

  public Root() {
    System.out.println("Root 的无参数构造器");
  }
}

class Mid extends Root {

  static {
    System.out.println("Mid 的静态初始化快");
  }

  {
    System.out.println("Mid 的普通初始化快");
  }

  public Mid() {
    System.out.println("Mid 的无参数构造器");
  }

  public Mid(String msg) {
    // 通过this调用同一类中重载的构造器
    this();
    System.out.println("Mid 的带参数构造器，其参数值： " + msg);
  }
}

class Leaf extends Mid {

  static {
    System.out.println("Leaf 的静态初始化快");
  }

  {
    System.out.println("Leaf 的普通初始化快");
  }

  public Leaf() {
    super("Leeeeeeef!!!");
    System.out.println("Leaf 的无参数构造器");
  }
}
