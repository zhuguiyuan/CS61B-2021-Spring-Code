package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    /* Add some items and check whether size of correct. */
    @Test
    public void addAndSizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
        ad.addLast(3);
        assertEquals(1, ad.size());
        ad.addFirst(7);
        assertEquals(2, ad.size());
    }

    /* Add some items and then remove them. */
    @Test
    public void addAndRemoveTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("Last");
        ad.addFirst("Middle");
        ad.addFirst("First");
        assertEquals("First", ad.removeFirst());
        assertEquals(2, ad.size());
        assertEquals("Last", ad.removeLast());
        assertEquals(1, ad.size());
        assertEquals("Middle", ad.removeLast());
        assertEquals(0, ad.size());
    }

    /* Add and remove with a large number of operations. */
    @Test
    public void addAndRemoveLarge() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            assertEquals(i, ad.size());
            ad.addFirst(i);
        }
        assertEquals(N, ad.size());
        for (int i = 0; i < N; i++) {
            assertEquals((Integer) i, ad.removeLast());
            assertEquals(N - 1 - i, ad.size());
        }
    }

    /* Test removeFirst and removeLast in an empty ArrayDeque. */
    @Test
    public void removeInEmptyArrayDequeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull(ad.removeFirst());
        assertEquals(0, ad.size());
        assertNull(ad.removeLast());
        assertEquals(0, ad.size());
    }

    /* Test get method. */
    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull(ad.get(0));
        assertNull(ad.get(-10));
        assertNull(ad.get(10));
        ad.addFirst(3);
        assertEquals((Integer) 3, ad.get(0));
        assertNull(ad.get(-10));
        assertNull(ad.get(10));
        ad.addFirst(7);
        assertEquals((Integer) 7, ad.get(0));
        assertEquals((Integer) 3, ad.get(1));
        assertNull(ad.get(-10));
        assertNull(ad.get(10));
    }

    /* Test equal method. */
    @Test
    public void equalityTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        assertEquals(ad1, ad2);
        for (int i = 0; i < 500; i++) {
            ad1.addLast(i);
        }
        for (int i = 499; i >= 0; i--) {
            ad2.addFirst(i);
        }
        assertEquals(ad1, ad2);
        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        assertNotEquals(ad3, ad1);
        assertNotEquals("OtherType", ad1);
    }

    /* Test Equality between ArrayDeque and LinkedListDeque. */
    @Test
    public void equalityBetweenArrayDequeAndLinkedListDeque() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        assertEquals(ad, lld);
        for (int i = 0; i < 100; i++) {
            ad.addFirst(i);
            lld.addFirst(i);
            assertEquals(ad, lld);
        }
    }

    /* Test equality between LinkedListDeque containing items of different types. */
    @Test
    public void equalityBetweenDifferentItemType() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        assertEquals(ad1, ad2); // The same as standard library.
        ad1.addFirst(0);
        ad2.addFirst("");
        assertNotEquals(ad1, ad2);
    }

    /* Test for-each loop in ArrayDeque. */
    @Test
    public void forEachLoopIteration() {
        Integer[] expected = new Integer[]{7, 8, 4, 2, 5, 9, 0, 8, 1};
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (Integer i : expected) {
            ad.addLast(i);
        }
        int idx = 0;
        for (Integer i : ad) {
            assertEquals(expected[idx], i);
            idx += 1;
        }
    }
}
