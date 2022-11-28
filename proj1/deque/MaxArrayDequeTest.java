package deque;

import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    private static class LengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    private static class alphabetComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    /* Test basic get maximum. */
    public void basicMaximumTest() {
        String[] data = new String[]{"hello", "my", "friends"};
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new LengthComparator());
        assertNull(mad.max());
        for (String s : data) {
            mad.addLast(s);
        }
        assertEquals("friends", mad.max());
    }

    @Test
    /* Test get maximum with another comparator. */
    public void getMaximumWithAnotherComparator() {
        String[] data = new String[]{"hello", "my", "friends"};
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new LengthComparator());
        Comparator<String> c = new alphabetComparator();
        assertNull(mad.max(c));
        for (String s : data) {
            mad.addLast(s);
        }
        assertEquals("my", mad.max(c));
    }
}
