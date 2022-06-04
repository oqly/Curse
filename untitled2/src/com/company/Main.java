package com.company;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String output = "";
        Operation[] operations = new Operation[] {
                new ComplexOperation(1,10, 100),
                new ComplexOperation(2,5, 20),
                new ComplexOperation(3,15, 100),
                new ComplexOperation(4,30, 300),
                new ComplexOperation(5,25, 400),
                new SimpleOperation(1,10),
                new SimpleOperation(2,5),
                new SimpleOperation(3,15),
                new SimpleOperation(4,30),
                new SimpleOperation(5,25)
        };
        Worker[] workers = new Worker[]{
                new Worker("Угрюмов", "Арсений", 2),
                new Worker("Голубков", "Никита", 3),
                new Worker("Тюленев", "Сергей", 1),
                new Worker("Кузьменко", "Илья", 2),
                new Worker("Смирнов", "Андрей", 1)
        };
        InfoGiver[] objects = new InfoGiver[operations.length + workers.length];
        for (int i = 0; i < operations.length; i++){
            objects[i] = operations[i];
        }
        for (int i = 0; i < workers.length; i++){
            objects[operations.length + i] = workers[i];
        }
        int[] chainOfOperations = new int[]{9, 3, 4, 2, 5, 0, 8, 7, 1, 6};
        int totalTime = 0;
        int totalCost = 0;

        int[] chainOfTemporary = new int[chainOfOperations.length - 1];
        int[] chainOfCosts = new int[chainOfOperations.length - 1];

        for (int i=0; i < (chainOfOperations.length - 1); i++){
            totalTime = totalTime + operations[chainOfOperations[i]].getExecutionTime();
            totalCost = totalCost + operations[chainOfOperations[i]].getCost();
            chainOfTemporary[i] = operations[chainOfOperations[i]].getExecutionTime();
            chainOfCosts[i] = operations[chainOfOperations[i]].getCost();
        }

        int countSO = 1;
        int countCO = 1;
        int countW = 1;
        for (int i = 0; i < objects.length; i++) {
            output += objects[i].getInfo();

        }

        System.out.println("Общее время выполнения: " + totalTime);
        System.out.println("Общая стоимость выполнения: " + totalCost);
        System.out.println("Цепочка времени:" + Arrays.toString(chainOfTemporary));
        System.out.println("цепочка затрат:" + Arrays.toString(chainOfCosts));
        System.out.println(output);
    }
}
