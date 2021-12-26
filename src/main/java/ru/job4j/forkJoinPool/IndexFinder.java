package ru.job4j.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexFinder<T> {
    private static <T>  int linearSearch(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int find(T[] array, T element) {
        if (array.length <= 10) {
            return linearSearch(array, element);
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new Task<T>(array, element, 0, array.length - 1));
    }
}

class Task<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T element;
    private final int from;
    private final int to;

    Task(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (from == to) {
            return array[from].equals(element) ? from : -1;
        }
        int mid = (from + to) / 2;
        Task<T> leftTask = new Task<T>(array, element, from, mid);
        Task<T> rightTask = new Task<T>(array, element, mid + 1, to);
        leftTask.fork();
        rightTask.fork();
        int left = leftTask.join();
        int right = rightTask.join();
        return Integer.max(left, right);
    }
}
