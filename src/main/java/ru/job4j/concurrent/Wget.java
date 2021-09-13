package ru.job4j.concurrent;

import java.util.stream.IntStream;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
                IntStream.range(1, 101).forEach(i -> {
                    System.out.print("\rLoading : " + i  + "%");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        });
        thread.start();
    }
}
