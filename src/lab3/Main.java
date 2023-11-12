package lab3;

import lab3.implementations.queue.ArrayQueue;
import lab3.implementations.queue.DequeQueue;
import lab3.implementations.queue.LinkedListQueue;
import lab3.implementations.stack.ArrayStack;
import lab3.implementations.stack.LinkedListStack;
import lab3.implementations.stack.VectorStack;

public class Main {
    public static void main(String[] args) {

        //Ques
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(10);
        DequeQueue<String> dequeQueue = new DequeQueue<>();
        LinkedListQueue<Double> linkedListQueue = new LinkedListQueue<>();

        arrayQueue.enqueue(5);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(7);
        System.out.println(arrayQueue.dequeue());

        dequeQueue.enqueue("5");
        dequeQueue.enqueue("6");
        dequeQueue.enqueue("7");
        System.out.println(dequeQueue.dequeue());

        linkedListQueue.enqueue(5.0);
        linkedListQueue.enqueue(6.0);
        linkedListQueue.enqueue(7.0);
        System.out.println(linkedListQueue.dequeue());

        //Stacks
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
        LinkedListStack<String> linkedListStack = new LinkedListStack<>();
        VectorStack<Double> vectorStack = new VectorStack<>();

        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.push(7);
        System.out.println(arrayStack.pop());

        linkedListStack.push("5");
        linkedListStack.push("6");
        linkedListStack.push("7");
        System.out.println(linkedListStack.pop());

        vectorStack.push(5.0);
        vectorStack.push(6.0);
        vectorStack.push(7.0);
        System.out.println(vectorStack.pop());

    }
}
