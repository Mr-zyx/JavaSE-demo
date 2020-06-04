package reference_demo;

import java.lang.ref.WeakReference;

/**
 * 弱引用的特点是不管内存是否足够，只要发生GC，都会被回收。弱引用在很多地方都有用到，比如ThreadLocal、WeakHashMap。
 */
public class WeakReferenceTest {

  public static void main(String[] args) {
    WeakReference<Object> weakReference = new WeakReference<>(new Object());
    System.out.println(weakReference.get());

    System.gc();
    System.out.println(weakReference.get());

  }
}
