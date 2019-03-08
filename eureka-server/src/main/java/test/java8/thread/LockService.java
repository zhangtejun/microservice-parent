package test.java8.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockService {
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void await_a() {
        try {
            lock.lock();
            System.out.println("await time ； " + System.currentTimeMillis());
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void await_b() {
        try {
            lock.lock();
            System.out.println("await time ； " + System.currentTimeMillis());
            conditionB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal_a() {
        try {
            lock.lock();
            System.out.println("signal time ； " + System.currentTimeMillis());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signal_b() {
        try {
            lock.lock();
            System.out.println("signal time ； " + System.currentTimeMillis());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean hasV = false;

    public void set() {
        try {
            lock.lock();
            while (hasV) {
                conditionB.await();
            }
            System.out.println("打印A");
            hasV = true;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (!hasV) {
                conditionB.await();
            }
            System.out.println("B");
            hasV = false;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
