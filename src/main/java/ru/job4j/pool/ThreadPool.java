package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import ru.job4j.wait.SimpleBlockingQueue;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            threads.add(thread);
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        Runnable job1 = () -> {
            try {
                Thread.sleep(10000);
                System.out.println("job1");
            } catch (InterruptedException e) {
                System.out.println("job1 was interrupted");
                Thread.currentThread().interrupt();
            }
        };
        Runnable job2 = () -> System.out.println("job2");
        pool.work(job1);
        pool.work(job2);
        pool.shutdown();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}