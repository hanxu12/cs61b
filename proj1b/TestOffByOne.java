import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
   // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    //Alphabetical chars
    public void testEqualchar1() {
       // OffByOne obo = new OffByOne();
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
    }

    //Alphabetical chars
    public void testEqualchar2() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('x', 'y'));
        assertTrue(obo.equalChars('x', 'w'));
    }

    //Alphabetical chars returns false
    public void testEqualchar3() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('a', 'a'));
    }

    //Non-alphabetical characters
    public void testEqualchar4() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('&', '%'));
    }

    //Capital letters should return false
    public void testEqualchar5() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('A', 'a'));
        assertFalse(obo.equalChars('G', 'g'));
    }

}
