package ru.mail.polis.ads.part1;

        import java.util.Scanner;

class Queue {
    private final int SIZE;
    int[] myList;
    int end;
    int begin;

    public Queue(int size) {
        this.SIZE = size + 5;
        myList = new int[this.SIZE];
        end = 0;
        begin = 0;
    }

    //Добавить в очередь число n (значение n задается после команды). Программа должна вывести ok.
    public void push(int obj) {
        myList[end] = obj;
        end = (end + 1) % SIZE;
    }

    //    Удалить из очереди первый элемент. Программа должна вывести его значение.
    public int pop() {
        int ans = myList[begin];
        begin = (begin + 1) % SIZE;
        return ans;
    }

    //    Программа должна вывести значение первого элемента, не удаляя его из очереди.
    public int front() {
        return myList[begin];
    }

    //    Программа должна вывести количество элементов в очереди.
    public int size() {
        if (end < begin)
            return SIZE - (begin - end);
        return end - begin;
    }

    //    Программа должна очистить очередь и вывести ok.
    public void clear() {
        end = 0;
        begin = 0;
    }
}

public class task4 {
    public static void main(String[] args) {
        Queue myQueue = new Queue(100);
        Scanner in = new Scanner(System.in);
        String str = in.next();
        while (!str.equals("exit")) {
            if (str.equals("push")) {
                int val = in.nextInt();
                myQueue.push(val);
                System.out.println("ok");
            }
            if (str.equals("pop")) {
                System.out.println(myQueue.pop());
            }
            if (str.equals("front")) {
                System.out.println(myQueue.front());
            }
            if (str.equals("size")) {
                System.out.println(myQueue.size());

            }
            if (str.equals("clear")) {
                myQueue.clear();
                System.out.println("ok");
            }
            str = in.next();
        }
        System.out.println("bye");
    }
}
