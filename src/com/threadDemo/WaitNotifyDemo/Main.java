package com.threadDemo.WaitNotifyDemo;

import java.util.concurrent.TimeUnit;

public class Main {
    private int signal=0;
    public static void main(String[] args) {
        Main main=new Main();
        SetTarget set=new SetTarget(main);
        GetTarget get=new GetTarget(main);
        //开启线程
        new Thread(get).start();
        new Thread(get).start();
        new Thread(get).start();
        new Thread(get).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(set).start();

    }

    public synchronized void set(){
        signal=1;
        //notify方法会随机叫醒一个处于wait状态的线程
        //notify();//叫醒单个线程
        notifyAll();//叫醒所有线程
        System.out.println("叫醒线程叫醒后休眠开始。。。");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int get(){
        System.out.println(Thread.currentThread().getName()+"方法开始执行了");

        if(signal!=1){
            try {
                wait();
                System.out.println("叫醒之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"方法执行完毕。。。");
        return signal;
    }

}
