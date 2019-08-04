/** Performs some basic ArrayDeque tests. */
public class ArrayDequeTest {
    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
    * finally printing the results. 
    *
    * && is the "and" operation. */
    public static void getTest() {
        System.out.println("Running gettest.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        int a = ad1.removeFirst();
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addLast(8);
        int b = ad1.get(4);
        ad1.addFirst(10);
        ad1.addFirst(11);
        int c = ad1.removeLast();
        ad1.addFirst(5);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards.
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty 
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty 
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }
*/

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        getTest();
        
    }
} 
