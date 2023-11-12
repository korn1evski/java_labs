package lab3.implementations.queue;

import lab3.interfaces.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    private Node<E> front, rear;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
