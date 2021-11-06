package ru.job4j.wait;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void positiveCase() throws InterruptedException {
        AtomicReference<String> x = new AtomicReference<>();
        String element = "text";
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>();
        Thread first = new Thread(() -> queue.offer(element));
        Thread second = new Thread(() -> x.set(queue.poll()));
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(element, x.get());
    }
}