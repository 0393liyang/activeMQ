package com.threadDemo.WaitNotifyDemo;

/**
 * 消费线程
 */
public class VendorGetTarget implements Runnable {
    private Vendor vendor;

    public VendorGetTarget(Vendor vendor) {
        this.vendor=vendor;
    }

    @Override
    public void run() {
        while (true){
            vendor.consumers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
