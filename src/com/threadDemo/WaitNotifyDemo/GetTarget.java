package com.threadDemo.WaitNotifyDemo;

public class GetTarget  implements  Runnable{
    private Main main;

    public GetTarget(Main main){
        this.main=main;
    }
    @Override
    public void run() {
        main.get();

    }
}
