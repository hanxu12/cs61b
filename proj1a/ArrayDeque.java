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
        if (index == items.length) {
            return 0;
        }
        return index + 1;
    }

    //** Resizes the underlying array to the target capacity. */
    private void resize(int capacity){
        T[] a = (T [])new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length){
            resize(2 * size);
        }
        items[nextfirst] = item;
        size += 1;
        nextfirst = minusOne(nextfirst);
    }

    public void addLast(T item) {
        if (size == items.length){
            resize(2 * size);
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
        if (index > items.length | index < 0) {
            return null;
        }
        if (items[index] == null){
            return null;
        }
        return items[index];
    }
}

