public class LList<T> {
    private Node<T> head;
    private Node<T> tail;
    protected Node<T> current;
    int length;

    LList() {
        current = head = tail = new Node<T>(null);
        length = 0;
    }

    public void clear() {
        head.setNext(null);
        current = head = tail = new Node<T>(null);
        length = 0;
    }

    public void insert(T val) {
        current.setNext(new Node<T>(val, current.next()));
        if(tail == current) {
            tail = current.next();
        }
        length++;
    }

    public void append(T val) {
        tail = tail.setNext(new Node<T>(val, null));
        length++;
    }

    public T remove() {
        if(current.next() == null) return null;
        T val = current.next().value();
        if(tail == current.next()) tail = current;
        current.setNext(current.next().next());
        length--;
        return val;
    }

    public void moveToStart() {
        current = head;
    }

    public void moveToEnd() {
        current = tail;
    }

    public void prev() {
        if(current == head) return;
        Node<T> tmp = head;
        while(tmp.next() != current) {
            tmp = tmp.next();
        }
        current = tmp;
    }
    
    public void next() {
        if(current != tail) {
            current = current.next();
        }
    }

    public int length() { return length; }

    public int currPos() {
        Node<T> tmp = head;
        int i = 0;
        while(tmp != current) {
            tmp = tmp.next();
            i++;
        }
        return i;
    }

    public void moveToPos(int position) {
        if(position < 0 || position > length) return; 

        current = head;

        for(int i = 0; i < position; i++) {
            current = current.next();
        }
    }

    public T getValue() {
        if(current.next() == null) return null;
        return current.next().value();
    }

    //converts LList to string
    public String toString() {
        int oldPosition = currPos();

        StringBuffer output = new StringBuffer((length + 1) * 4); 

        moveToStart();

        while(current.next() != null) {
            output.append(getValue());
            next();
        }

        moveToPos(oldPosition);
        return output.toString();
    }
}
