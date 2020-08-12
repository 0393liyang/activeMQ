package com.threadDemo;

import java.util.concurrent.Callable;

public class main {
    private static final Object lock=new Object();
    public static void main(String[] args) {
        RunA runA=new RunA("1111");
        RunA runB=new RunA("2222");
       // runA.run();//方法调用
        runA.setDaemon(true);//守护线程，子线程随着主线程main 一起死亡
        runA.start();//线程启动
       // runA.setPriority(10);//线程优先级1~10
        //Thread.yield();
        /*runB.start();*/
        //runA.stop();//暴力不优雅
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //runA.interrupt();//配合下面的 !isInterrupted()


    }
    static class RunA extends Thread{
        private String index;

        public RunA(String index){
            this.index=index;
        }
        @Override
        public void run(){
            super.run();
            /*synchronized (lock){
                try {
                    System.out.println(index+"start");
                    this.wait();
                    System.out.println(index+"end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }*/
            while (!isInterrupted()){
                System.out.println("11111");
            }

        }

    }
    static class RunB implements Runnable{

        @Override
        public void run(){

        }
    }
    static class RunC implements Callable<String>{
        @Override
        public String call()throws Exception{
            return "RunC";
        }
    }
}
