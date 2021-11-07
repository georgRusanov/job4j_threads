package ru.job4j.wait;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertEquals(buffer, Arrays.asList(0, 1, 2, 3, 4));
    }
}