package ru.job4j.forkJoinPool;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;
import org.junit.Test;

public class IndexFinderTest {

    @Test
    public void findElementInArraySizeBiggerThanTen() {
        Object[] array = Stream.iterate(1, n -> n + 1).limit(40).toArray();
        int actualIndex = IndexFinder.find(array, 9);
        assertEquals(actualIndex, 8);
    }

    @Test
    public void noElementInArraySizeBiggerThanTen() {
        Object[] array = Stream.iterate(1, n -> n + 1).limit(40).toArray();
        int actualIndex = IndexFinder.find(array, 60);
        assertEquals(actualIndex, -1);
    }

    @Test
    public void findElementInArraySizeTen() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int actualIndex = IndexFinder.find(array, 9);
        assertEquals(actualIndex, 8);
    }

    @Test
    public void findElementInArraySizeLessThanTen() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int actualIndex = IndexFinder.find(array, 9);
        assertEquals(actualIndex, 8);
    }

    @Test
    public void noElementInArraySizeLessThanTen() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int actualIndex = IndexFinder.find(array, 13);
        assertEquals(actualIndex, -1);
    }
}