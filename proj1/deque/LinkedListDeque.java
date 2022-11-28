package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private final LinkedListNode<T> sentinel;

    private static class LinkedListNode<T> {
        T item;
        LinkedListNode<T> prev;
        LinkedListNode<T> next;

        private LinkedListNode(T item) {
            this.item = item;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkedListNode<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        LinkedListNode<T> newNode = new LinkedListNode<>(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        LinkedListNode<T> newNode = new LinkedListNode<>(item);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        String[] result = new String[size()];
        int idx = 0;
        for (T item : this) {
            result[idx++] = item.toString();
        }
        System.out.println(String.join(" ", result));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deque<?>) {
            Deque<T> that = (Deque<T>) obj;
            if (size() != that.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(that.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkedListNode<T> node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        node.next = node.prev = null;
        size -= 1;
        return node.item;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        LinkedListNode<T> node = sentinel.prev;
        sentinel.prev = node.prev;
        node.prev.next = sentinel;
        node.next = node.prev = null;
        size -= 1;
        return node.item;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        LinkedListNode<T> curr = sentinel.next;
        while (index > 0 && curr != sentinel) {
            curr = curr.next;
            index -= 1;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, LinkedListNode<T> curr) {
        if (index == 0 || curr == sentinel) {
            return curr.item;
        }
        return getRecursiveHelper(index - 1, curr.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private LinkedListNode<T> curr;

        LinkedListDequeIterator() {
            curr = sentinel;
        }

        @Override
        public boolean hasNext() {
            return curr.next != sentinel;
        }

        @Override
        public T next() {
            curr = curr.next;
            return curr.item;
        }
    }
}
