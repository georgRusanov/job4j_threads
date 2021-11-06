package ru.job4j.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class CacheTest {

    @Test
    public void shouldAddTwoElements() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        assertEquals(List.of(base1, base2), List.of(cache.get(1), cache.get(2)));
    }

    @Test
    public void shouldUpdateCache() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        boolean isUpdated = cache.update(new Base(1, 0));
        assertTrue(isUpdated);
        assertEquals(1, cache.get(1).getVersion());
    }

    @Test (expected = OptimisticException.class)
    public void shouldThrowAnErrorWithDifferentVersion() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        cache.update(new Base(1, 1));
    }

    @Test
    public void shouldDeleteElement() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        assertNotNull(cache.get(1));
        cache.delete(base1);
        assertNull(cache.get(1));
        assertEquals(base2, cache.get(2));
    }
}