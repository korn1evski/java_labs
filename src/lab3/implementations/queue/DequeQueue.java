package lab3.implementations.queue;
import lab3.interfaces.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeQueue<E> implements Queue<E> {
    private Deque<E> deque = new ArrayDeque<>();

    public void enqueue(E item) {
        deque.addLast(item);
    }

    public E dequeue() {
        return deque.removeFirst();
    }

    public E peek() {
        return deque.peekFirst();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }
}
