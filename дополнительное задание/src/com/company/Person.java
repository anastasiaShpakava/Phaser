package com.company;

import java.util.concurrent.BlockingQueue;

public class Person extends Thread{
    private BlockingQueue queue1;
    public Person (BlockingQueue queue){
        this.queue1=queue;
    }
    @Override
    public void run () {
        for (int i=0; i<=20; i++){
            try {
                queue1.take();
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
