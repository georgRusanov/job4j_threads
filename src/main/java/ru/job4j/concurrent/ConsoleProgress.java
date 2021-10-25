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

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}
