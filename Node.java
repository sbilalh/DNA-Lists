/**
 * CSC 172 - Lab 4
 * Bilal Hussain (shussa11@u.rochester.edu)
 * Sammy Potter (spott14@u.rochester.edu)
 * 26 February 2022
 * See README.txt
 */

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
