package ru.job4j.wait;

import java.util.LinkedList;
import java.util.Queue;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int maxCapacity;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= maxCapacity) {
            this.wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        T element = queue.poll();
        notifyAll();
        return element;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
