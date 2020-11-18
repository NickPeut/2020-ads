package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;
    private int sizeBst;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            right = null;
            left = null;
        }
    }

    public RedBlackBst() {
        this.root = null;
        sizeBst = 0;
    }

    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }

    Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }

    void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        return x != null ? x.value : null;
    }

    private Node get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        if (cmp > 0)
            return get(x.right, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node put(Node x, Key key, Value value) {
        if (x == null) {
            sizeBst++;
            return new Node(key, value, RED);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        return fixUp(x);
    }

    Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left))
                    x = moveRedLeft(x);
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (cmp == 0 && x.right == null)
                return null;
            else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left))
                    x = moveRedRight(x);
                if (x.key == key) {
                    Node min = min(x.right);
                    assert min != null;
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else
                    x.right = delete(x.right, key);
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) return null;
        root = delete(root, key);
        root.color = BLACK;
        sizeBst--;
        return value;
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        return x == null ? null : x.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        return x == null ? null : x.value;
    }

    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        return max(x.right);
    }

    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        return x == null ? null : x.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }

    private Key floor(Node x, Key key, Key maxKey) {
        if (x == null) return maxKey;
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            return floor(x.left, key, maxKey);
        } else if (cmp < 0) {
            return floor(x.right, key, x.key);
        } else {
            return x.key;
        }
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }

    private Key ceil(Node x, Key key, Key minKey) {
        if (x == null) return minKey;
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            if (minKey == null || minKey.compareTo(x.key) > 0) {
                return floor(x.left, key, x.key);
            }
            return floor(x.left, key, minKey);
        } else if (cmp < 0) {
            return floor(x.right, key, x.key);
        }
        return x.key;
    }

    @Override
    public int size() {
        return sizeBst;
    }
}