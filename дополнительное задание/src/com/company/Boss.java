package com.company;

import java.util.concurrent.BlockingQueue;

public class Boss extends Thread {
    private BlockingQueue  queue2;



    public Boss(BlockingQueue queue2) {
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
queue2.;
            System.out.println("Boss " + i + " wants to print");

        }
    }
}
