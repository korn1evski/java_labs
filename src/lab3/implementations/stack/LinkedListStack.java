package lab3.implementations.stack;

import lab3.interfaces.Stack;
public class LinkedListStack<E> implements Stack<E> {
    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

    private Node<E> top = null;

    @Override
    public void push(E item) {
        Node<E> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E result = top.element;
        top = top.next;
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.element;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}

