package lab3.implementations.queue;

import lab3.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {
    private E[] arr;
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
    }

    public void enqueue(E item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
