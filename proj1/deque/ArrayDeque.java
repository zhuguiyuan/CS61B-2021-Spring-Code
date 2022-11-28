package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 7;
        tail = 0;
    }

    private int nextIndex(int curr) {
        return (curr + 1) % items.length;
    }

    private int prevIndex(int curr) {
        return (curr - 1 + items.length) % items.length;
    }

    private void checkResize() {
        if (size == items.length) {
            if (size < 1024) {
                resize(size * 2);
            } else {
                resize((int) (size * 1.25));
            }
        } else if (items.length > 8 && size * 4 < items.length) {
            resize(items.length / 2);
        }
    }

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int curr = nextIndex(head);
        for(int i = 0; i < size; i += 1) {
            newItems[i] = items[curr];
            curr = nextIndex(curr);
        }
        tail = size;
        head = newItems.length - 1;
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        checkResize();
        items[head] = item;
        head = prevIndex(head);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        checkResize();
        items[tail] = item;
        tail = nextIndex(tail);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String[] result = new String[size()];
        int idx = 0;
        for (T item : this) {
            result[idx++] = item.toString();
        }
        return String.join(" ", result);
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        head = nextIndex(head);
        T item = items[head];
        items[head] = null;
        size -= 1;
        checkResize();
        return item;
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        tail = prevIndex(tail);
        T item = items[tail];
        items[tail] = null;
        size -= 1;
        checkResize();
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        int realIndex = (head + 1 + index) % items.length;
        return items[realIndex];
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
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int curr;

        public ArrayDequeIterator() {
            curr = nextIndex(head);
        }

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public T next() {
            T item = items[curr];
            curr = nextIndex(curr);
            return item;
        }
    }
}
