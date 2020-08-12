package com.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolPKTest {
    public static void main(String[] args) {
        Long start=System.currentTimeMillis();
        final List<Integer> l=new ArrayList<>();
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        final Random random=new Random();
        for (int i=0;i<10000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    l.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);//等待1天所有子线程都走完 在进行主线程的下面代码的进行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("时间："+(System.currentTimeMillis()-start));
        System.out.println(l.size());
    }
}
