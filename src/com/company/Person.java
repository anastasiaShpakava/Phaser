package com.company;

import java.util.concurrent.Semaphore;

public class Person extends Thread {
    private static final boolean[] FREE_PRINTER = new boolean[2];
    Semaphore printer;

    @Override
    public void run() {
        System.out.println(this.getName() + " waiting for printer");
        try {
            printer.acquire();
            int freePrinter = -1;
            synchronized (FREE_PRINTER) {
                for (int i = 0; i < 2; i++) {
                    if (!FREE_PRINTER[i]) {
                        FREE_PRINTER[i] = true;
                        freePrinter = i;
                        System.out.println(this.getName() + " using printer");
                        break;
                    }
                }
            }
            this.sleep(1000);
            synchronized (FREE_PRINTER) {
                FREE_PRINTER[freePrinter] = false;// освободили принтер
            }
            printer.release();
            System.out.println(this.getName() + " release printer");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



