package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableRealize<Key, Value> implements HashTable<Key, Value> {
    private int size = 0;
    private int capacity = 16;
    private List<Key, Value>[] table;

    HashTableRealize() {
        this(16);
    }

    HashTableRealize(int sz) {
        capacity = sz;
        size = 0;
        table = new List[capacity];
        for (int i = 0; i < capacity; ++i) {
            table[i] = new List<Key, Value>();
        }
    }

    private static class Node<Key, Value> {
        Key key;
        Value value;
        Node<Key, Value> next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private class List<Key, Value> {
        Node<Key, Value> head;

        public List(Key key, Value val) {
            head = new Node<Key, Value>(key, val);
        }

        public List() {
        }

        public Value get(Key key) {
            Node<Key, Value> node = head;
            while (node != null && !node.key.equals(key)) {
                node = node.next;
            }
            return node == null ? null : node.value;
        }

        public void put(Key key, Value val) {
            Node<Key, Value> node = head;
            while (node != null && node.next != null && !node.key.equals(key)) {
                node = node.next;
            }
            if (node.key.equals(key)) {
                node.value = val;
            } else {
                node.next = new Node<Key, Value>(key, val);
                size++;
            }
        }

        public Value remove(Key key) {
            if (head.key.equals(key)) {
                size--;
                Value val = head.value;
                head = head.next;
                return val;
            }
            Node<Key, Value> prev = head;
            Node<Key, Value> node = head.next;
            while (node != null && !node.key.equals(key)) {
                prev = prev.next;
                node = node.next;
            }
            if (node == null)
                return null;

            size--;
            prev.next = node.next;
            return node.value;
        }

        public boolean contains(Key key) {
            return this.get(key) != null;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        return table[hash(key)].get(key);
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hashKey = hash(key);
        return table[hashKey] != null && table[hashKey].contains(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value val) {
        if (size >= capacity)
            resizeTable();
        int hashKey = hash(key);
        if (table[hashKey].head == null) {
            table[hashKey] = new List<Key, Value>(key, val);
            size++;
        } else
            table[hashKey].put(key, val);
    }

    private void resizeTable() {
        int prevCapacity = capacity;
        capacity *= 2;
        List<Key, Value>[] prevTable = table;
        table = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new List<>();
        }
        int prevSize = size;
        size = 0;
        for (int i = 0; i < prevSize; i++) {
            if (prevTable[i] == null) continue;
            Node<Key, Value> temp = prevTable[i].head;
            while (temp != null) {
                this.put(temp.key, temp.value);
                temp = temp.next;
            }
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hashKey = hash(key);
        if (table[hashKey].head == null) return null;
        return table[hashKey].remove(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
