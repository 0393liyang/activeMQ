package com.threadDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable implements java.util.concurrent.Callable<String> {

    public static void main(String[] args) {
        FutureTask futureTask=new FutureTask(new Callable());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        return "多线程编程";
    }
}
