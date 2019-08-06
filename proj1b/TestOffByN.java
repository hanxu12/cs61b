import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testOffByN1(){
        OffByN offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));
    }

    @Test
    public void testOffByN2(){
        OffByN offBy4 = new OffByN(4);
        assertTrue(offBy4.equalChars('a', 'e'));
    }

}
