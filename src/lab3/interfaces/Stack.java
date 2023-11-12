package lab3.interfaces;

public interface Stack<E> {
    void push(E item);
    E pop();
    E peek();
    boolean isEmpty();
}

