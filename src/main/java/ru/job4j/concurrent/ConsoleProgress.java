package ru.job4j.concurrent;

import java.util.Arrays;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] lastSymbol = {"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            Arrays.stream(lastSymbol).forEach(s -> System.out.print("\r load: ..." + s));
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        progress.interrupt();
    }
}
