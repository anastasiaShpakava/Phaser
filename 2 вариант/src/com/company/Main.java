package com.company;

import java.util.concurrent.Semaphore;

public class Main {
    private static final boolean[] FREE_PRINTER = new boolean[2];
    private static final Semaphore SEMAPHORE = new Semaphore(2, true);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 20; i++) {
            new Thread(new Person(i)).start();
            Thread.sleep(400);
        }
    }
    public static class Person extends Thread {
        private int numberOfPerson;

        public Person(int numberOfPerson) {
            this.numberOfPerson = numberOfPerson;
        }
        @Override
        public void run() {
            System.out.println("Person number " + numberOfPerson + " is waiting for free printer");
            try {
                SEMAPHORE.acquire();
                int freePrinter = -1;
                synchronized (FREE_PRINTER) {
                    for (int i = 0; i < 2; i++) {
                        if (!FREE_PRINTER[i]) {
                            FREE_PRINTER[i] = true;
                            freePrinter = i;
                            System.out.println("Person number " + numberOfPerson + " is using printer" + i);
                            break;
                        }
                    }
                }
                Thread.sleep(5000);       // печатаем
                synchronized (FREE_PRINTER) {
                    FREE_PRINTER[freePrinter] = false;// освободили принтер
                }
                SEMAPHORE.release();
                System.out.println("Person number " + numberOfPerson + " freed the printer ");
            } catch (InterruptedException e) {
            }
        }
    }
}

