package lab3.implementations.stack;

import lab3.interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {
    private E[] arr;
    private int top = -1;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
    }

    @Override
    public void push(E item) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full");
        }
        arr[++top] = item;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top--];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
