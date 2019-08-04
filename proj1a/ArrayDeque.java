public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nfirst;
    private int nlast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nfirst = 3;
        nlast = 4;
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
    private void resizeup(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, nlast + 1);
        System.arraycopy(items, nfirst, a, nfirst + items.length, items.length - nlast - 1);
        if (nfirst != 0) {
            nfirst = nfirst + items.length;
        }
        items = a;
    }

    private void resizedown(int capacity) {
        // nfirst > nlast

        // nfirst < nlast
        T[] a = (T []) new Object[capacity];

        if (nfirst > nlast) {
            System.arraycopy(items, nfirst, a, 0, items.length - nfirst);
            System.arraycopy(items, 0, a, items.length - nfirst, nlast + 1);
        } else {
            System.arraycopy(items, nfirst, a, 0, size + 2);
        }
        nfirst = 0;
        nlast = size + 1;
        items = a;
    }

    public void addFirst(T item) {
        if (nfirst == plusOne(nlast)) {
            resizeup(2 * items.length);
        }
        items[nfirst] = item;
        size += 1;
        nfirst = minusOne(nfirst);
    }

    public void addLast(T item) {
        if (nfirst == plusOne(nlast)) {
            resizeup(2 * items.length);
        }
        items[nlast] = item;
        size += 1;
        nlast = plusOne(nlast);
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
        int p = plusOne(nfirst);
        int counter = 0;
        while (counter <= size) {
            p = plusOne(p);
            counter = counter + 1;
            System.out.print("items[p]" + " ");
        }
    }

    public T removeFirst() {
        if (size > 0) {
            if ((float) size / items.length < 0.25) {
                resizedown(items.length / 2);
            }
            nfirst = plusOne(nfirst);
            size -= 1;
            return items[nfirst];
        }
        return null;
    }

    public T removeLast() {
        if (size > 0) {
            if ((float) size / items.length < 0.25) {
                resizedown(items.length / 2);
            }
            nlast = minusOne(nlast);
            if (size > 0) {
                size -= 1;
            }
            return items[nlast];
        }
        return null;
    }
/**
    public T get(int index) {
        if (index >= items.length - 2 | index < 0) {
            return null;
        }
        int p = plusOne(nfirst);
        p = p + index;
        if (items[p] == null){
            return null;
        }
        return items[p];
    }
*/
    public T get(int index) {
        int p = plusOne(nfirst);
        while (index > 0) {
            p = plusOne(p);
            index = index - 1;
        }
        if (items[p] == null) {
            return null;
        }
        return items[p];
    }
}

