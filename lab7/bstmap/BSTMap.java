package bstmap;

import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode<K, V> root;

    public BSTMap() {
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

    private BSTNode<K, V> insert(BSTNode<K, V> root, K key, V value) {
        if (root == null) {
            return new BSTNode<>(key, value);
        }
        int compare = key.compareTo(root.key);
        if (compare < 0) {
            root.left = insert(root.left, key, value);
        } else if (compare > 0) {
            root.right = insert(root.right, key, value);
        } else {
            root.value = value;
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
        if (compare < 0) {
            return search(root.left, key);
        } else if (compare > 0) {
            return search(root.right, key);
        } else {
            return root;
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
        Set<K> result = new TreeSet<>();
        for (K key : this) {
            result.add(key);
        }
        return result;
    }

    @Override
    public V remove(K key) {
        V value = get(key);
        root = delete(root, key);
        return value;
    }

    @Override
    public V remove(K key, V value) {
        if (value.equals(get(key))) {
            root = delete(root, key);
        }
        return value;
    }

    private BSTNode<K, V> delete(BSTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.key);
        if (compare < 0) {
            root.left = delete(root.left, key);
        } else if (compare > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            BSTNode<K, V> tmp = root;
            root = min(tmp.right);
            root.right = deleteMin(tmp.right);
            root.left = tmp.left;
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private BSTNode<K, V> min(BSTNode<K, V> root) {
        assert root != null;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private BSTNode<K, V> deleteMin(BSTNode<K, V> root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private final Stack<BSTNode<K, V>> stack;

        BSTMapIterator() {
            stack = new Stack<>();
            pushLeftToLeaf(root);
        }

        private void pushLeftToLeaf(BSTNode<K, V> root) {
            if (root != null) {
                stack.push(root);
                pushLeftToLeaf(root.left);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            BSTNode<K, V> curr = stack.pop();
            if (curr.right != null) {
                pushLeftToLeaf(curr.right);
            }
            return curr.key;
        }
    }

    /**
     * Prints out BSTMap in order of increasing Key.
     * Used for Debugging.
     */
    public void printInOrder() {
        for (K key : this) {
            System.out.printf("%s ", key);
        }
        System.out.println();
    }
}
