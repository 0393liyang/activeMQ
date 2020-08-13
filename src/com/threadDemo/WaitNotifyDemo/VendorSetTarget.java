package com.threadDemo.WaitNotifyDemo;

/**
 * 生产线程
 */
public class VendorSetTarget implements Runnable {
    private Vendor vendor;

    public VendorSetTarget(Vendor vendor){
        this.vendor=vendor;
    }
    @Override
    public void run() {
        while (true){
            vendor.production();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
