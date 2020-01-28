package com.company;

import java.util.concurrent.Semaphore;

public class Main {
    private static final Semaphore printer = new Semaphore(2, true);

    public static void main(String[] args) {
        for (int i = 0; i <= 20; i++) {
            Person person = new Person();
            person.printer = printer;
            person.start();
        }
    }
}
