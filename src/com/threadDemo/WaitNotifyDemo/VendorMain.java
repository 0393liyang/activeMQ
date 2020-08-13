package com.threadDemo.WaitNotifyDemo;

public class VendorMain {
    public static void main(String[] args) {
        Vendor vendor=new Vendor();
        VendorSetTarget set=new VendorSetTarget(vendor);
        VendorGetTarget get=new VendorGetTarget(vendor);
        //开启生产线程
        new Thread(set).start();
        new Thread(set).start();
        new Thread(set).start();
        new Thread(set).start();
        //开启消费线程
        new Thread(get).start();
        new Thread(get).start();
        new Thread(get).start();
        //new Thread(get).start();
    }
}
