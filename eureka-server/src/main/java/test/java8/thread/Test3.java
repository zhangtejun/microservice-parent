package test.java8.thread;

import org.junit.Test;

public class Test3 {
    static  class  ThreadA extends Thread {
        private LockService lockService;
        ThreadA(LockService lockService){
            this.lockService = lockService;
        }

        @Override
        public void run() {
            lockService.await_a();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        LockService lockService = new LockService();
        ThreadC thread = new ThreadC(lockService);

        thread.start();
        ThreadD thread1 = new ThreadD(lockService);
        thread1.start();
    }



    static  class ThreadC extends Thread{
        private LockService lockService;
        ThreadC(LockService lockService){
            this.lockService = lockService;
        }

        @Override
        public void run() {
           for (int i =0;i<3; i++){
               lockService.get();
           }
        }
    }
    static  class ThreadD extends Thread{
        private LockService lockService;
        ThreadD(LockService lockService){
            this.lockService = lockService;
        }

        @Override
        public void run() {
            for (int i =0;i<3; i++){
                lockService.set();
            }
        }
    }
}
