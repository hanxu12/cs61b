import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once CharacterComparator interface & OffByOne class created.
    //Alphabetical chars
    @Test
    public void testEqualchar1() {
       // OffByOne offByOne = new OffByOne();
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
    }

    //Alphabetical chars
    @Test
    public void testEqualchar2() {
        //OffByOne offByOne = new OffByOne();
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('x', 'w'));
    }

    //Alphabetical chars returns false
    @Test
    public void testEqualchar3() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    //Non-alphabetical characters
    @Test
    public void testEqualchar4() {
        //OffByOne offByOne = new OffByOne();
        assertTrue(offByOne.equalChars('&', '%'));
    }

    //Capital letters vs non-capital shld return false;
    @Test
    public void testEqualchar5() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('G', 'g'));
    }

    //Capital letter should return false;
    @Test
    public void testEqualchar6() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('g', 'H'));
    }

    //Test non-alphabetical vs letters;
    @Test
    public void testEqualchar7() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('&', 'B'));
        assertFalse(offByOne.equalChars('&', '('));
    }

    //Test non-alphabetical vs letters;
    @Test
    public void testEqualchar8() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('B', 'G'));
        assertTrue(offByOne.equalChars('B', 'C'));
        assertFalse(offByOne.equalChars('B', 'B'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('Z', 'X'));
        assertTrue(offByOne.equalChars('Z', 'Y'));
        //assertFalse(offByOne.equalChars('&', '^'));
    }

    @Test
    public void testEqualchar9() {
        //OffByOne offByOne = new OffByOne();
        assertFalse(offByOne.equalChars('!', '@'));
        assertFalse(offByOne.equalChars('*', '^'));
        assertFalse(offByOne.equalChars('a', '*'));
        assertFalse(offByOne.equalChars('A', '*'));
        assertFalse(offByOne.equalChars('B', ')'));
        assertFalse(offByOne.equalChars('Z', ')'));
        assertFalse(offByOne.equalChars('(', 'Y'));
        assertTrue(offByOne.equalChars('(', ')'));
        assertTrue(offByOne.equalChars(' ', '!'));
    }

    @Test
    public void testEqualchar10() {
        //OffByOne offByOne = new OffByOne();
        assertTrue(offByOne.equalChars('0', '1'));
        assertTrue(offByOne.equalChars('2', '3'));
        assertTrue(offByOne.equalChars('4', '5'));
        assertTrue(offByOne.equalChars('8', '7'));
        assertFalse(offByOne.equalChars('9', '0'));
        assertFalse(offByOne.equalChars('7', '3'));
        assertFalse(offByOne.equalChars('2', '8'));
        assertFalse(offByOne.equalChars('1', '9'));
        assertFalse(offByOne.equalChars(' ', '9'));
    }
}
