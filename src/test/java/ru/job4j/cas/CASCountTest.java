package ru.job4j.cas;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CASCountTest {

    @Test
    public void shouldIncrementCounterFromDifferentThreads() throws InterruptedException {
        final CASCount count = new CASCount();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(count::increment));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        assertEquals(count.get(), 5);
    }
}
