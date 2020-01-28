package com.company;

import sun.plugin2.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue queue1 = new ArrayBlockingQueue<>(20);
        BlockingQueue queue2 = new ArrayBlockingQueue<>(5);
        Person person = new Person(queue1);
        Boss boss = new Boss(queue2);
        new Thread(person).start();
        new Thread(boss).start();
    }
}
