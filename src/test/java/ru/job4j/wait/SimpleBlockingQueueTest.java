package ru.job4j.wait;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void positiveCase() throws InterruptedException {
        AtomicReference<String> x = new AtomicReference<>();
        String element = "text";
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(10);
        Thread first = new Thread(() -> {
            try {
                queue.offer(element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread second = new Thread(() -> {
            try {
                x.set(queue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(element, x.get());
    }
}