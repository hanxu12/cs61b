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

    public int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    public int plusOne(int index) {
        if (index == items.length) {
            return 0;
        }
        return index + 1;
    }

//int minusOne(int index)
    public void addFirst(T item) {
        items[nextfirst] = item;
        size += 1;
        nextfirst = minusOne(nextfirst);
    }

    public void addLast(T item) {
        items[nextlast] = item;
        size += 1;
        nextlast = plusOne(nextlast);
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    public void printDeque() {
        int p = plusOne(nextfirst);
        int counter = 0;
        while (counter <= size){
            p = plusOne(p);
            counter = counter + 1;
            System.out.print("items[p]" + " ");
        }
    }
//Remove first, i.e. remove item[nextfirst + 1]
    public T removeFirst() {
        //whether this is necessary?
        nextfirst = plusOne(nextfirst);
        size -= 1;
        return items[nextfirst];

    }

    //Remove last, i.e. remove item[nextlast - 1]
    public T removeLast() {
        nextlast = minusOne(nextlast);
        size -= 1;
        return items[nextlast];
    }

    public T get(int index){
        if (items[index] == null){
            return null;
        }
        return items[index];
    }
}