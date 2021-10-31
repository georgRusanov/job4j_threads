package ru.job4j.synch;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertEquals(Set.of(1, 2), rsl);
    }
}