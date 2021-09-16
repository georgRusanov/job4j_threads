package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            while (true) {
                long startTime = System.currentTimeMillis();
                int bytesRead = in.read(dataBuffer, 0, 1024);
                if (bytesRead == -1) {
                    return;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finishTime = System.currentTimeMillis();
                long duration = (finishTime - startTime) / 1000;
                if (speed > duration) {
                    Thread.sleep((speed - duration) * 1000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}