package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] lastSymbol = {"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            for (String element : lastSymbol) {
                System.out.print("\r load: ..." + element);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
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
