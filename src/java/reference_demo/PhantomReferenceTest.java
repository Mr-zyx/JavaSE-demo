package reference_demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用通常用在NIO，管理堆外内存
 */
public class PhantomReferenceTest {

  public static void main(String[] args) {
    // 虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，如果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的ReferenceQueue中。
    ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
    PhantomReference<byte[]> reference = new PhantomReference<>(new byte[1], referenceQueue);
    // 无法通过虚引用来获取对一个对象的真实引用。
    System.out.println(reference.get()); // null

    Reference poll = referenceQueue.poll();
    System.out.println("虚引用队列现在有值么？" + poll);
    System.gc();
    Reference poll2 = referenceQueue.poll();
    System.out.println("虚引用可能被回收了：" + poll2);


  }
}
