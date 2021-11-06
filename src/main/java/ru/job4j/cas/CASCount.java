package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int value = count.get();
        count.compareAndSet(value, ++value);
    }

    public int get() {
        return count.get();
    }
}
