public class LinkedListDeque<T> {

    public IntNode sentinel;
    public int size;

    public class IntNode{
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n){
            prev = p;
            item = i;
            next = n;
        }
    }


    public LinkedListDeque(){
        sentinel = new IntNode(null,null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel = new IntNode(null,null, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        size = 1;
    }

    public void addFirst(T item){
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }

    public void addLast(T item){
        sentinel.prev.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode curr = sentinel;
        while (curr.next != sentinel){
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(" ");
    }

    public T removeFirst(){
        IntNode old = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return old.item;
    }

    public T removeLast(){
        IntNode old = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return old.item;
    }

    public T get(int index){
        IntNode curr = sentinel;
        while (index > 0){
            curr = curr.next;
            index -= 1;
        }
        return curr.next.item;

    }

    public T getRecursive(int index){
        /**
        IntNode curr = sentinel;
        if (index == 0){
            return curr.item;
        }
        curr = curr.next;
        return getRecursive(index - 1);
         */
        return null;

    }


    public LinkedListDeque(LinkedListDeque other){
        

    }


}
