public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextfirst = 3;
        nextlast = 4;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    //** Resizes the underlying array to the target capacity. */
    private void resizeup(int capacity){
        T[] a = (T [])new Object[capacity];
        System.arraycopy(items, 0, a,0, nextlast + 1);
        System.arraycopy(items, nextfirst ,a,nextfirst + items.length, items.length - nextlast - 1);
        nextfirst = nextfirst + items.length;
        items = a;

    }

    public void addFirst(T item) {
        if (nextfirst - nextlast == 1){
            resizeup(2 * items.length);
        }
        items[nextfirst] = item;
        size += 1;
        nextfirst = minusOne(nextfirst);
    }

    public void addLast(T item) {
        if (nextfirst - nextlast == 1){
            resizeup(2 * items.length);
        }
        items[nextlast] = item;
        size += 1;
        nextlast = plusOne(nextlast);
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = plusOne(nextfirst);
        int counter = 0;
        while (counter <= size) {
            p = plusOne(p);
            counter = counter + 1;
            System.out.print("items[p]" + " ");
        }
    }

    public T removeFirst() {
        nextfirst = plusOne(nextfirst);
        if (size > 0) {
            size -= 1;
        }
        return items[nextfirst];

    }

    public T removeLast() {
        /**
        if (size/ items.length < 0.25){
            resize(items.length / 2);
        }
         */
        nextlast = minusOne(nextlast);
        if (size > 0){
            size -= 1;
        }
        return items[nextlast];
    }

    public T get(int index) {
        if (index >= items.length - 2 | index < 0) {
            return null;
        }
        int p = plusOne(nextfirst);
        p = p + index;
        if (items[p] == null){
            return null;
        }
        return items[p];
    }
}

