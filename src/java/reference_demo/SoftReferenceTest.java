package reference_demo;

import java.lang.ref.SoftReference;

/**
 * 软引用：
 * <p>
 * 当内存不足，会触发JVM的GC，如果GC后，内存还是不足，就会把软引用的包裹的对象给干掉，也就是只有在内存不足，JVM才会回收该对象。 软引用通常用作缓存
 * <p>
 * 运行时需要设置JVM参数 -Xmx20M
 */
public class SoftReferenceTest {

  public static void main(String[] args) {

    // 创建一个软引用
    SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
    System.out.println(softReference.get());
    // 提示JVM进行GC
    System.gc();
    System.out.println(softReference.get());

    // 当我们创建了一个10M的byte[]后，最大堆内存不够了，所以把软引用对象包裹的byte[]给干掉了
    byte[] bytes = new byte[1024 * 1024 * 10];

    System.out.println(softReference.get());
  }

}
