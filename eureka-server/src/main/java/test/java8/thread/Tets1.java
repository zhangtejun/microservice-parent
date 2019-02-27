package test.java8.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
public class Tets1 {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i=0 ;i<5000;i++){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("已是停止状态");
            }
            System.out.println("i="+i);
        }
        };
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(20);
        t.interrupt();// 虽然是在t对象上调用interrupt方法，
        //Thread.currentThread().interrupt();
        System.out.println( Thread.currentThread().getName());
        System.out.println("是否停止1 ： "+t.isInterrupted());// false
        System.out.println("是否停止2 ： "+t.isInterrupted());// false 当前线程为main,它未中断。
    }

    @Test
    public void t1(){
        Thread.currentThread().interrupt();
        System.out.println("是否停止1 ： "+Thread.interrupted());// true  interrupted判断当前线程是否已是停止状态，该方法具有清除状态功能
        System.out.println("是否停止2 ： "+Thread.interrupted());// false
    }

    @Test
    public void t2() throws InterruptedException {
        MyThread[] myThreads = new  MyThread[100];

        for (int i =0 ; i<100;i++){
            myThreads[i] = new MyThread();
        }

        for (int i =0 ; i<100;i++){
            myThreads[i].start();
        }
        //Thread.sleep(30000);
    }
}
