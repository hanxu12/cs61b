import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    //Test edge case which word length is 0;
    @Test
    public void testisPalindrome1() {
        boolean actual = palindrome.isPalindrome("");
        assertTrue(actual);
    }

    //Test edge case which word length is 1;
    @Test
    public void testisPalindrome2(){
        boolean actual = palindrome.isPalindrome("a");
        assertTrue(actual);
    }

    //Test normal case which length is 2;
    @Test
    public void testisPalindrome3(){
        boolean actual = palindrome.isPalindrome("ao");
        assertFalse(actual);
    }

    //Test normal case which length is 3 with 3 same characters
    @Test
    public void testisPalindrome4(){
        boolean actual = palindrome.isPalindrome("aaa");
        assertTrue(actual);
    }

    //Test normal case which length is 4 with 4 different characters
    @Test
    public void testisPalindrome5(){
        boolean actual = palindrome.isPalindrome("abcd");
        assertFalse(actual);
    }

    //Test normal case which length is 5 but is a palindrome
    @Test
    public void testisPalindrome6(){
        boolean actual = palindrome.isPalindrome("abcba");
        assertTrue(actual);
    }

    //Test normal case with capital letters and non-capital letters
    @Test
    public void testisPalindrome7(){
        boolean actual = palindrome.isPalindrome("AaaAa");
        assertFalse(actual);
    }

    //
    @Test
    public void testisPalindromeCC1(){
        CharacterComparator obo = new OffByOne();
        boolean actual = palindrome.isPalindrome("flake", obo);
        assertTrue(actual);
    }

    @Test
    public void testisPalindromeCC2(){
        CharacterComparator obo = new OffByOne();
        boolean actual = palindrome.isPalindrome("acefdb", obo);
        assertTrue(actual);
    }

    //Test a normal Palindrome is not a PalindromeCC
    @Test
    public void testisPalindromeCC3(){
        CharacterComparator obo = new OffByOne();
        boolean actual = palindrome.isPalindrome("aaa", obo);
        assertFalse(actual);
    }
}
