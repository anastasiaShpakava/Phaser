package com.company;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(2); // 2 зарегистрированных потока.
        // можно потоки регисрировать и так: в конструкторе Washer: phaser.register();
        // в методе run: phaser.arriveAndDeregister();
        new Washer(phaser);
        new Washer(phaser);
    }
    static class Washer extends Thread { //// мойщики, которые моют машины. их у нас 2
        Phaser phaser;
        public Washer(Phaser phaser) {
            this.phaser = phaser;
            start(); // запускаем поток
        }
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {  // моем 3 машины
                System.out.println(getName() + " is washing the car");
                phaser.arriveAndAwaitAdvance(); // ждем, пока все мойщики не закончат мойку(кто-то моет салон,
                // кто-то стекла и т.д.)
                // когда мойщики закончат мыть эту машину, следующая машина может заезжать на мойку.
            }
        }
    }
}
