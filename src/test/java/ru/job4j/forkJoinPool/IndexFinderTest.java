package ru.job4j.forkJoinPool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IndexFinderTest {

    @Test
    public void findElementInArraySizeBiggerThanTen() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int actualIndex = IndexFinder.find(array, 9);
        assertEquals(actualIndex, 8);
    }

    @Test
    public void noElementInArraySizeBiggerThanTen() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int actualIndex = IndexFinder.find(array, 13);
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