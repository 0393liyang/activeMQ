package com.threadDemo.WaitNotifyDemo;

public class SetTarget implements Runnable {
    private Main main;

    public SetTarget(Main main){
        this.main=main;
    }
    @Override
    public void run() {
        main.set();

    }
}
