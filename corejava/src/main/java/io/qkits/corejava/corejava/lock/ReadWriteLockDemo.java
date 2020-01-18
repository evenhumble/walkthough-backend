package io.qkits.corejava.corejava.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: patrick on 2019-07-12
 * @Description:
 */
public class ReadWriteLockDemo {


  private static ReadWriteLockDemo readWriteLockExample = new ReadWriteLockDemo();

  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private Map<String, Object> cache = new HashMap<String, Object>();

  public Object read(String key){
    readWriteLock.readLock().lock();
    Object obj = null;
    try{
      obj=cache.get(key);
      if(obj==null){
        readWriteLock.readLock().unlock();
        readWriteLock.writeLock().lock();
        try {
          if(obj == null){
            obj = "search db for answer";
            cache.put(key, obj);
          }
        }finally {
          // 当前线程在获取到写锁的过程中，可以获取到读锁，这叫锁的重入，然后导致了写锁的降级，称为降级锁。
          // 利用重入可以将写锁降级，但只能在当前线程保持的所有写入锁都已经释放后，才允许重入 reader使用
          // 它们。所以在重入的过程中，其他的线程不会有获取到锁的机会（这样做的好处）。试想，先释放写锁，在
          // 上读锁，这样做有什么弊端？--如果这样做，那么在释放写锁后，在得到读锁前，有可能被其他线程打断。
          // 重入————>降级锁的步骤：先获取写入锁，然后获取读取锁，最后释放写入锁（重点）
          readWriteLock.readLock().lock();
          readWriteLock.writeLock().unlock();
        }
      }
    }finally {
      readWriteLock.readLock().unlock();
    }

    return obj;
  }

  public static void main(String[] args) {
    readWriteLockExample.read("test");
  }
}
