package ru.mail.polis.ads.part3;

import java.util.ArrayList;
import java.util.Scanner;

class HeapMax {
    private final ArrayList<Integer> list;
    private int size = 0;

    HeapMax() {
        list = new ArrayList<>();
        list.add(0);
    }

    public int getSize() {
        return size;
    }

    public void insert(int v) {
        size++;
        list.add(v);
        int i = size;
        while (i > 1 && list.get(i) > list.get(i / 2)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public int top() {
        return list.get(1);
    }

    public int pop() {
        int max = list.get(1);
        swap(1, size);
        size--;
        int k = 1;
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && list.get(j) < list.get(j + 1)) j++;
            if (list.get(k) >= list.get(j)) break;
            swap(k, j);
            k = j;
        }
        list.remove(size + 1);
        return max;
    }

    private void swap(Integer i, Integer j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}

class HeapMin {
    private final ArrayList<Integer> list;
    private int size = 0;

    HeapMin() {
        list = new ArrayList<>();
        list.add(0);
    }

    public void insert(int v) {
        size++;
        list.add(v);
        int i = size;
        while (i > 1 && list.get(i) < list.get(i / 2)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public int getSize() {
        return size;
    }

    public int top() {
        return list.get(1);
    }

    public int pop() {
        int max = list.get(1);
        swap(1, size);
        size--;
        int k = 1;
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && list.get(j) > list.get(j + 1)) j++;
            if (list.get(k) <= list.get(j)) break;
            swap(k, j);
            k = j;
        }
        list.remove(size + 1);
        return max;
    }

    private void swap(Integer i, Integer j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}

public class median {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HeapMax heapMax = new HeapMax();
        HeapMin heapMin = new HeapMin();
        int mid = Integer.MAX_VALUE;
        while (in.hasNextInt()) {
            int a = in.nextInt();
            if (heapMax.getSize() == 0 && heapMin.getSize() == 0) {
                heapMin.insert(a);
            }
            System.out.println();
        }
    }
}
