package test.java8.thread;

import org.junit.Test;

public class Test2
{
    public static void main(String[] args) throws InterruptedException {
        String lock = new String();
        MyThread1 myThread1 = new MyThread1(lock);
        myThread1.start();
        Thread.sleep(4000);
        MyThread2 myThread2 = new MyThread2(lock);
        myThread2.start();
        System.out.println(5);
    }

    static class MyThread1 extends Thread{
        private Object lock;
        public MyThread1(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("1");
            synchronized (lock){
                System.out.println(2);
                try {
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(3);
            }
            System.out.println(4);
        }
    }
    static class MyThread2 extends Thread{
        private Object lock;
        public MyThread2(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("11");
            synchronized (lock){
                System.out.println(12);
                    lock.notify();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(13);//执行完synchronized 代码块后，才释放锁
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(14);
        }
    }

    @Test
    public  void a() throws InterruptedException {
        String lock = new String();
        MyThread1 myThread1 = new MyThread1(lock);
        myThread1.start();
        myThread1.join();
        System.out.println(5);// myThread1 run方法执行完成后，再执行由当前线程继续执行
        Thread.sleep(9000);
        System.out.println(5);
    }

    @Test
    public void b(){
        Thread thread = new Thread(()-> {
            System.out.println(11);
            System.out.println(1);
        });
        thread.start();
    }

}
