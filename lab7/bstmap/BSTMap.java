package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode<K, V> root;

    BSTMap() {
        root = null;
    }

    private static class BSTNode<K, V> {
        private int size = 1;
        private final K key;
        private V value;
        private BSTNode<K, V> left;
        private BSTNode<K, V> right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode insert(BSTNode<K, V> root, K key, V value) {
        if (root == null) {
            return new BSTNode(key, value);
        }
        int compare = key.compareTo(root.key);
        if (compare == 0) {
            root.value = value;
        } else if (compare < 0) {
            root.left = insert(root.left, key, value);
        } else {
            root.right = insert(root.right, key, value);
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private int size(BSTNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return search(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> result = search(root, key);
        if (result == null) {
            return null;
        }
        return result.value;
    }

    private BSTNode<K, V> search(BSTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.key);
        if (compare == 0) {
            return root;
        } else if (compare < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Prints out BSTMap in order of increasing Key.
     * Used for Debugging.
     */
    public void printInOrder() {

    }
}
