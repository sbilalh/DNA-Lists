public class Node<T> {
    private T value;
    private Node<T> next;

    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    Node(Node<T> next) {
        this.next = next;
    }

    Node<T> next() { return next; }
    Node<T> setNext(Node<T> next) { return this.next = next; }

    T value() { return value; }
    T setElement(T value) { return this.value = value; }
}
