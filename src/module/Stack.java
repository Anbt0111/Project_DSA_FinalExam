package module;

public class Stack<T> {
    private Node<T> top;
    private int size;

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }
    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node<T> current = top;
        int indexFromTop = size - 1 - i;

        for (int j = 0; j < indexFromTop; j++) {
            current = current.next;
        }

        return current.data;
    }
}