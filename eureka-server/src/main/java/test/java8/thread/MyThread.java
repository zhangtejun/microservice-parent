package test.java8.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MyThread extends Thread{
    //public volatile static int count;
    public  static AtomicInteger count = new AtomicInteger(0);
    public synchronized static void addCount(){
        for (int i =0 ; i<100;i++){
            log.info("count:"+count.incrementAndGet());
        }

    }

    @Override
    public void run() {
        addCount();
    }
}
