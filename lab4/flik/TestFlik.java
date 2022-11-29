package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlik {

    @Test
    public void equalityRatherIdentityTest() {
        Integer a = 1000;
        Integer b = 500;
        b += 500;
        assertTrue(Flik.isSameNumber(a, b));
    }

    @Test
    public void equalityWithNullTest() {
        Integer a = null;
        Integer b = null;
        assertTrue(Flik.isSameNumber(a, b));
        assertTrue(Flik.isSameNumber(b, a));
        b = 3;
        assertFalse(Flik.isSameNumber(a, b));
        assertFalse(Flik.isSameNumber(b, a));
    }
}
