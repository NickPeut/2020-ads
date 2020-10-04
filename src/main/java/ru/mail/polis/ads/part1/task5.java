package ru.mail.polis.ads.part1;

import java.util.Scanner;

class Stack {
    private static class Node {
        int item;
        Node prev;

        Node(int element) {
            this.item = element;
            this.prev = null;
        }
    }

    Node head;

    //Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.
    public void push(int n) {
        Node newNode = new Node(n);
        newNode.prev = head;
        head = newNode;
    }


    //Удалить из стека последний элемент. Программа должна вывести его значение.
    public int pop() throws Exception {
        if (head == null)
            throw new Exception();

        int val = head.item;
        head = head.prev;

        return val;
    }

    //Программа должна вывести значение последнего элемента, не удаляя его из стека.
    public int back() throws Exception {
        if (head == null) throw new Exception();
        return head.item;
    }

    //Программа должна вывести количество элементов в стеке.
    public int size() {
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.prev;
        }
        return size;
    }

    //Программа должна очистить стек и вывести ok.
    public void clear() {
        head = null;
    }
}

public class task5 {
    public static void main(String[] args) {
        Stack myStack = new Stack();
        Scanner in = new Scanner(System.in);
        String str = in.next();
        while (!str.equals("exit")) {
            try {
                if (str.equals("push")) {
                    int val = in.nextInt();
                    myStack.push(val);
                    System.out.println("ok");
                }
                if (str.equals("pop")) {
                    System.out.println(myStack.pop());
                }
                if (str.equals("back")) {
                    System.out.println(myStack.back());
                }
                if (str.equals("size")) {
                    System.out.println(myStack.size());

                }
                if (str.equals("clear")) {
                    myStack.clear();
                    System.out.println("ok");
                }
            } catch (Exception e) {
                System.out.println("error");
            }
            str = in.next();
        }
        System.out.println("bye");
    }
}
