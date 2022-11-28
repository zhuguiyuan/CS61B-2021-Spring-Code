package tester;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayDequeEC {
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
                for (Integer item : ad) {
                    assertEquals(curr, item);
                    curr -= 1;
                }
            }
        }
    }

}
