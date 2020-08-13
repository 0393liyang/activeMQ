package com.threadDemo.WaitNotifyDemo;

/**
 * 定义生产消费者
 */
public class Vendor {
    //定义库存数量
    private int count;
    //定义最大库存
    private final int MAX_COUNT=10;

    public synchronized void production(){
        while (count>=MAX_COUNT){
            System.out.println(Thread.currentThread().getName()+"库存数量达到最大值，停止生产。");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //否则
        count++;
        System.out.println(Thread.currentThread().getName()+"货品正在生产，当前库存为："+count);
        //库存充足唤醒所有线程
        notifyAll();
    }
    public synchronized void consumers(){
        while (count<=0){
            System.out.println(Thread.currentThread().getName()+"没有商品了，消费者处于等待状态。。。");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName()+"正在消费，当前库存"+count);

        notifyAll();
    }
}
