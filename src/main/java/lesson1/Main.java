package lesson1;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//      1. Создать builder для класса Person со следующими полями:
//          String firstName, String lastName, String middleName, String country, String address, String phone, int age, String gender.

        Person person = Person.getBuilder().address("sad").country("sd").age(12).build();
        System.out.println(person+"\n");


        //      2. Описать ошибки в коде (см. задание в методичке) и предложить варианты оптимизации.

/**
                                                -----Ошибки------

    1. в классе Lorry интерфейсы Moveable и Stopable должны имлементироваться, а не расширяться классом.
                    исправляем:       class Lorry extends Car implements Moveable, Stopable

    2. в классе Lorry нет реализации метода open(), унаследованного от Car

    3. отсутствует класс Engine. Необходимо реализовать в соответствии с ТЗ :)


                                                ---оптимизация----
    1. Думаю, стоит имплементировать интерфейс Movable и Stopable в классе Car.
                Во первых тогда наследникам не забудут реализовать тормозную систему, как в данном случае легковесной машине.
                Во вторых данные интерфейсы будут работать при обращении с объектом, как с классом-родителем (полиморфизм).
                В данном случае реализация одинаковая, поэтому реализовать методы интерфейса можно в абстрактом классе-родителе

    2. Сделать классы публичными для возможности использования из других пакетов.

    3. Поле engine в Car сделать приватным.

    4. Непонятная переменная Object start в Car

    5. Изменить модификатор метода start в Car на публичный (или абстрактный, отдав реализацию наследникам).


**/


//      3. Написать пример кода, который реализует принцип полиморфизма, на примере фигур — круг, квадрат, треугольник.

        List<Shape> shapes = Arrays.asList(new Rectangle(), new Round(), new Triangle());
        System.out.println("В листе Shape разные наследники:");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }

    }
}
