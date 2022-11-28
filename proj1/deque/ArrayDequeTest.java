package deque;

import edu.princeton.cs.algs4.StdRandom;
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
            assertEquals((Integer) i , ad.removeLast());
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

    @Test
    /* Randomized test for size, isEmpty, addFirst, addLast,
       removeFirst, removeLast, get, and getIterator. */
    public void randTest() {
        /* Invariant 1: cnt is always same as the size of lld. */
        int cnt = 0;
        /* Invariant 2: ad is always [x_0, x_1, ..., x_n],
           where x_0 = firstNum - 1 and x_n = lastNum + 1, and x_i = x_{i+1} + 1. */
        int firstNum = 3, lastNum = 2;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                assertEquals(cnt, ad.size());
                if (cnt == 0) {
                    assertTrue(ad.isEmpty());
                }
            } else if (operationNumber == 1) {
                cnt += 1;
                ad.addFirst(firstNum++);
            } else if (operationNumber == 2) {
                cnt += 1;
                ad.addLast(lastNum--);
            } else if (operationNumber == 3) {
                Integer item = ad.removeFirst();
                if (cnt == 0) {
                    assertNull(item);
                } else {
                    cnt -= 1;
                    assertEquals((Integer) (--firstNum), item);
                }
            } else if (operationNumber == 4) {
                Integer item = ad.removeLast();
                if (cnt == 0) {
                    assertNull(item);
                } else {
                    cnt -= 1;
                    assertEquals((Integer) (++lastNum), item);
                }
            } else if (operationNumber == 5) {
                if (ad.size() > 0) {
                    int index = StdRandom.uniform(ad.size());
                    Integer item = ad.get(index);
                    assertEquals((Integer) (firstNum - 1 - index), item);
                }
            } else if (operationNumber == 6) {
                Integer curr = firstNum - 1;
                for(Integer item : ad) {
                    assertEquals(curr, item);
                    curr -= 1;
                }
            }
        }
    }

    /* Test get method. */
    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull(ad.get(0));
        assertNull(ad.get(-10));
        assertNull(ad.get(10));
        ad.addFirst(3);
        assertEquals((Integer)3, ad.get(0));
        assertNull(ad.get(-10));
        assertNull(ad.get(10));
        ad.addFirst(7);
        assertEquals((Integer)7, ad.get(0));
        assertEquals((Integer)3, ad.get(1));
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
