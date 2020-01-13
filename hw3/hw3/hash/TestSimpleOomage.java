package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
//         Write a test that ensures the hashCode is perfect,
//          meaning no two SimpleOomages should EVER have the same
//          hashCode UNLESS they have the same red, blue, and green values!
        HashMap<SimpleOomage, Integer> hmap = new HashMap<>();
        for (int i = 0; i <= 255; i += 5) {
            for (int j = 0; j <= 255; j += 5) {
                for (int k = 0; k <= 255; k += 5) {
                    SimpleOomage ooDemo = new SimpleOomage(i, j, k);
                    hmap.put(ooDemo, hmap.getOrDefault(ooDemo, 0) + 1);
                }
            }
        }
        for (Integer cnt: hmap.values()) {
            assertTrue(cnt == 1);
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        SimpleOomage ooC = new SimpleOomage(0, 5, 10);
        SimpleOomage ooC2 = new SimpleOomage(5, 0, 0);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertTrue(ooC.hashCode() == ooC2.hashCode());
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        ArrayList<Oomage> oomages = new ArrayList<>();
        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
