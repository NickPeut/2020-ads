package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size;

    public AvlBst() {
        size = 0;
    }

    public AvlBst(Node node) {
        this.root = node;
        this.size = 1;
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node balance(Node x) {
        int fctX = factor(x);
        if (fctX == 2) {
            if (factor(x.left) < 0)
                x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (fctX == -2) {
            if (factor(x.right) > 0) x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }


    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) return null;
        Value ans = get(key);
        root = remove(root, key);
        return ans;
    }

    private Node remove(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);
        if (cmp < 0) {
            x.right = remove(x.right, key);
        } else if (cmp > 0) {
            x.left = remove(x.left, key);
        } else {
            size--;
            x = deleteNode(x);
        }
        return x;
    }

    private Node deleteNode(Node x) {
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node tmp = x;
        x = min(tmp.right);
        x.right = deleteMin(tmp.right);
        x.left = tmp.left;
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node tmp = x;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Key min() {
        Node x = min(root);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public Value minValue() {
        Node x = min(root);
        if (x == null) return null;
        return x.value;
    }

    @Override
    public Key max() {
        Node x = max(root);
        if (x == null) return null;
        return x.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node tmp = x;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    @Override
    public Value maxValue() {
        Node x = max(root);
        if (x == null) return null;
        return x.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            return floor(x.left, key);
        }
        if (cmp < 0) {
            Node tmp = floor(x.right, key);
            if (tmp == null) {
                return x;
            }
            return tmp;
        }
        return x;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp < 0) {
            return ceil(x.right, key);
        }
        if (cmp > 0) {
            Node tmp = ceil(x.left, key);
            if (tmp == null) {
                return x;
            }
            return tmp;
        }
        return x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    int height(Node x) {
        return x == null ? 0 : x.height;
    }
}
