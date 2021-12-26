package ru.job4j.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexFinder<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T element;
    private final int from;
    private final int to;

    private static <T>  int linearSearch(T[] array, T element, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int find(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexFinder<T>(array, element, 0, array.length - 1));
    }

    IndexFinder(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch(array, element, from, to);
        }
        int mid = (from + to) / 2;
        IndexFinder<T> leftTask = new IndexFinder<T>(array, element, from, mid);
        IndexFinder<T> rightTask = new IndexFinder<T>(array, element, mid + 1, to);
        leftTask.fork();
        rightTask.fork();
        int left = leftTask.join();
        int right = rightTask.join();
        return Integer.max(left, right);
    }
}
