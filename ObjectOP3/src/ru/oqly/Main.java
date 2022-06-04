package ru.oqly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //int[] chainop = new int[]{0, 3, 4, 2, 2, 0, 1, 2, 1, 7, 5, 6, 5, 7}; //цепочка операций
        List<Integer> chainop = new ArrayList<>();
        chainop.add(0);
        chainop.add(3);
        chainop.add(4);
        chainop.add(2);
        chainop.add(2);
        chainop.add(0);
        chainop.add(1);
        chainop.add(2);
        chainop.add(1);
        chainop.add(7);
        chainop.add(5);
        chainop.add(6);
        chainop.add(5);
        chainop.add(7);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(new ComplicatedOperation(1,10, 100));
        operations.add(new ComplicatedOperation(2,5, 20));
        operations.add(new ComplicatedOperation(3,15, 100));
        operations.add(new ComplicatedOperation(4,30, 300));
        operations.add(new ComplicatedOperation(5,25, 400));
        operations.add(new SimpleOperation(1,20));
        operations.add(new SimpleOperation(2,10));
        operations.add(new SimpleOperation(3,15));

        int countop = operations.size(); //количество операций
        int sizeop = chainop.size(); //количество элементов цепочки операций

        String inputstr = "Входные данные: \n";
        for (int j = 0; j < countop; j++) {
            inputstr += "Операция " + j + ": время - " + operations.get(j).getTimeop() + ", стоимость - " + operations.get(j).getCostop() + '\n';
        }
        System.out.println(inputstr);

        int sumtime = 0;
        int sumcost = 0;
        StringBuilder chainTime = new StringBuilder("Цепочка времени: ");
        StringBuilder chainCost = new StringBuilder("Цепочка затрат: ");
        StringBuilder strsum = new StringBuilder("Общие затраты: \n");

        for (int i = 0; i < sizeop; i++) {
            sumtime += operations.get(chainop.get(i)).getTimeop();
            sumcost += operations.get(chainop.get(i)).getCostop();
            chainTime.append(operations.get(chainop.get(i)).getTimeop()).append(" ");
            chainCost.append(operations.get(chainop.get(i)).getCostop()).append(" ");
        }

        for (int j = 0; j < countop; j++) {
            strsum.append(operations.get(j).getInfo(Collections.frequency(chainop, j)));
        }

        System.out.println("Суммарное время: " + sumtime + '\n' + "Суммарные затраты: " + sumcost + '\n' + chainTime + '\n' + chainCost + '\n' + strsum);

        System.out.println("Обработка входной коллекции");
        DataProcessor<Operation> operationStr = new DataProcessor<>();
        operationStr.setList(operations);

        try {
            List<Operation> processed = operationStr.processing(15, 10);
            processed.forEach(operation -> System.out.print(operation.getInfo(1)));
        }
        catch (MyException e) {
            System.out.println("Нижняя граница (" + e.getLower() + ") не может быть больше верхней (" + e.getUpper() + ")");
        }

        System.out.println("Поиск по времени выполнения");

        try {
            System.out.print(operationStr.matching(21).getInfo(1));
        }
        catch (MyNullException e) {
            System.out.println("Элемент с временем " + e.getMatch() + " не найден!");
        }
    }
}