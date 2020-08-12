package com.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadPKTest {
    public static void main(String[] args) {
        Long start=System.currentTimeMillis();
        final List<Integer> l=new ArrayList<>();
        final Random random=new Random();
        for (int i=0;i<10000;i++){
            Thread thread=new Thread(){
                public void run(){
                    l.add(random.nextInt());
                }
            };
            thread.start();
            try {
                thread.join();//等待所有子线程都走完 在进行主线程的下面代码的进行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("时间："+(System.currentTimeMillis()-start));
        System.out.println(l.size());
    }
}
