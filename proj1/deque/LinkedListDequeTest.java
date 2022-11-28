package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

        lld1.addLast(20);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeLast();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque. */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types. */
    public void multipleParamTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Randomized test for size, isEmpty, addFirst, addLast,
       removeFirst, removeLast, get, and getIterator. */
    public void randTest() {
        /* Invariant 1: cnt is always same as the size of lld. */
        int cnt = 0;
        /* Invariant 2: lld is always [x_0, x_1, ..., x_n],
           where x_0 = firstNum - 1 and x_n = lastNum + 1, and x_i = x_{i+1} + 1. */
        int firstNum = 3, lastNum = 2;
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                assertEquals(cnt, lld.size());
                if (cnt == 0) {
                    assertTrue(lld.isEmpty());
                }
            } else if (operationNumber == 1) {
                cnt += 1;
                lld.addFirst(firstNum++);
            } else if (operationNumber == 2) {
                cnt += 1;
                lld.addLast(lastNum--);
            } else if (operationNumber == 3) {
                Integer item = lld.removeFirst();
                if (cnt == 0) {
                    assertNull(item);
                } else {
                    cnt -= 1;
                    assertEquals((Integer) (--firstNum), item);
                }
            } else if (operationNumber == 4) {
                Integer item = lld.removeLast();
                if (cnt == 0) {
                    assertNull(item);
                } else {
                    cnt -= 1;
                    assertEquals((Integer) (++lastNum), item);
                }
            } else if (operationNumber == 5) {
                if (lld.size() > 0) {
                    int index = StdRandom.uniform(lld.size());
                    Integer item = lld.get(index);
                    assertEquals((Integer) (firstNum - 1 - index), item);
                }
            } else if (operationNumber == 6) {
                Integer curr = firstNum - 1;
                for (Integer item : lld) {
                    assertEquals(curr, item);
                    curr -= 1;
                }
            }
        }
    }

    /* This test is extracted from randTest1 above. */
    @Test
    public void addLastThenAddFirst() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(3);
        assertEquals(1, lld.size());
        lld.addFirst(7);
        assertEquals(2, lld.size());
        assertEquals((Integer) 3, lld.removeLast());
        assertEquals(1, lld.size());
        assertEquals((Integer) 7, lld.removeLast());
        assertEquals(0, lld.size());
    }

    /* Test get method. */
    @Test
    public void getTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        assertNull(lld.get(0));
        assertNull(lld.get(-10));
        assertNull(lld.get(10));
        lld.addFirst(3);
        assertEquals((Integer) 3, lld.get(0));
        assertNull(lld.get(-10));
        assertNull(lld.get(10));
        lld.addFirst(7);
        assertEquals((Integer) 7, lld.get(0));
        assertEquals((Integer) 3, lld.get(1));
        assertNull(lld.get(-10));
        assertNull(lld.get(10));
    }

    /* Test getRecursive method. */
    @Test
    public void getRecursiveTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        assertNull(lld.getRecursive(0));
        assertNull(lld.getRecursive(-10));
        assertNull(lld.getRecursive(10));
        lld.addFirst(3);
        assertEquals((Integer) 3, lld.getRecursive(0));
        assertNull(lld.getRecursive(-10));
        assertNull(lld.getRecursive(10));
        lld.addFirst(7);
        assertEquals((Integer) 7, lld.getRecursive(0));
        assertEquals((Integer) 3, lld.getRecursive(1));
        assertNull(lld.getRecursive(-10));
        assertNull(lld.getRecursive(10));
    }

    /* Test equal method. */
    @Test
    public void equalityTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        assertEquals(lld1, lld2);
        for (int i = 0; i < 500; i++) {
            lld1.addLast(i);
        }
        for (int i = 499; i >= 0; i--) {
            lld2.addFirst(i);
        }
        assertEquals(lld1, lld2);
        LinkedListDeque<Integer> lld3 = new LinkedListDeque<>();
        assertNotEquals(lld3, lld1);
        assertNotEquals("OtherType", lld1);
    }

    /* Test equality between LinkedListDeque containing items of different types. */
    @Test
    public void equalityBetweenDifferentItemType() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        assertEquals(lld1, lld2); // The same as standard library.
        lld1.addFirst(0);
        lld2.addFirst("");
        assertNotEquals(lld1, lld2);
    }

    /* Test for-each loop in LinkedListDeque. */
    @Test
    public void forEachLoopIteration() {
        Integer[] expected = new Integer[]{7, 8, 4, 2, 5, 9, 0, 8, 1};
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for (Integer i : expected) {
            lld.addLast(i);
        }
        int idx = 0;
        for (Integer i : lld) {
            assertEquals(expected[idx], i);
            idx += 1;
        }
    }
}
