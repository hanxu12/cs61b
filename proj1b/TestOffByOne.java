import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
   // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    //Alphabetical chars
    @Test
    public void testEqualchar1() {
       // OffByOne obo = new OffByOne();
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
    }

    //Alphabetical chars
    @Test
    public void testEqualchar2() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('x', 'y'));
        assertTrue(obo.equalChars('x', 'w'));
    }

    //Alphabetical chars returns false
    @Test
    public void testEqualchar3() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('a', 'a'));
    }

    //Non-alphabetical characters
    @Test
    public void testEqualchar4() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('&', '%'));
    }

    //Capital letters should return false
    @Test
    public void testEqualchar5() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('A', 'a'));
        assertFalse(obo.equalChars('G', 'g'));
    }

    //Capital letter should return false;
    @Test
    public void testEqualchar6() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('A', 'B'));
        assertFalse(obo.equalChars('g', 'H'));
    }

    //Test non-alphabetical vs letters;
    @Test
    public void testEqualchar7() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('&', 'B'));
        assertFalse(obo.equalChars('&', '^'));
    }

    //Test non-alphabetical vs letters;
    @Test
    public void testEqualchar8() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('B', 'G'));
        //assertFalse(obo.equalChars('&', '^'));
    }
}
