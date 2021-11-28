import com.common.util.lock.ReentrantSpinLock;
import com.common.util.lock.SpinLock;
import javafx.concurrent.Task;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName LockTest.scala
 * @author: lulong
 * @Date: 2021/10/27
 * @Time: 13:33
 */
public class LockTest {
    public static void main(String[] args) {
        AtomicReference<Thread> cas = new AtomicReference<>();
        Thread t1 = new Thread(new Task(cas));
        Thread t2 = new Thread(new Task(cas));
        Thread t3 = new Thread(new Task(cas));
        t3.start();
        t1.start();
        t2.start();
    }

    static class Task implements Runnable {
        private AtomicReference<Thread> cas;
        private ReentrantSpinLock reentrantSpinLock;

        public Task(AtomicReference<Thread> cas) {
            this.cas = cas;
            this.reentrantSpinLock = new ReentrantSpinLock();
        }

        @Override
        public void run() {
            reentrantSpinLock.lock();
            reentrantSpinLock.lock();
            for(int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            reentrantSpinLock.unlock();
        }
    }
}
