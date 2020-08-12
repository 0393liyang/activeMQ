package com.threadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executors=Executors.newCachedThreadPool();
       // executors.execute(()-> System.out.println("多线程编程"));//JDK8
        executors.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("多线程");
            }
        });
    }
}
