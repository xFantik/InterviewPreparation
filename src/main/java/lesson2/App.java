package lesson2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {


    public static void main(String[] args) {


//    1. Реализовать основные методы связанного списка.
        linkedListTests();

//      2. Реализовать основные методы ArrayList.

        arrayListTests();


    }

    private static void arrayListTests() {
        List<String> list = new MyArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");


        System.out.println(list);

        list.add(2, "V");
        System.out.println(list);
    }

    private static void linkedListTests() {
        MyLinkedList<String> ls1 = new MyLinkedList<>();

        ls1.add("первый");
        ls1.add("второй");
        ls1.add("третий");
        ls1.add("четвертый");
        ls1.add("пятый");


//        System.out.println(ls.get(3));//


//        System.out.println(ls1.get(2));
//        System.out.println(ls1.size());
//        ls1.clear();
        System.out.println(ls1.size());
//        System.out.println(ls1.get(2));
        System.out.println(ls1.get(2));
//        ls1.set(2, "третий замененный");
        System.out.println(ls1.get(2));


        // ls1.set(3, "второй");


        System.out.println(ls1.indexOf("1"));


        System.out.println(ls1.contains("пятый"));
        System.out.println(ls1.contains("пят"));

//        ls1.add(0, "вставка");
        ls1.add(4, "вставка");

        System.out.println(ls1.getLast());

        for (int i = 0; i < ls1.size(); i++) {
            System.out.print(ls1.get(i) + ", ");
        }
        System.out.println();

//        ls.removeAll()

//        System.out.println(ls1.indexOf("пятый"));
//        System.out.println(ls1.lastIndexOf("пятый"));
//

        System.out.println(ls1.remove("вставка"));


        for (int i = 0; i < ls1.size(); i++) {
            System.out.print(ls1.get(i) + ", ");
        }
        System.out.println();


        ls1.add(5, "23");

        Iterator<String> iterator = ls1.iterator();


        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();

        System.out.println(ls1);
    }


}
