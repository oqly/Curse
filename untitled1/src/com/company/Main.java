package com.company;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Operation[] operations = new Operation[] {
                new Operation(10, 100),
                new Operation(5, 20),
                new Operation(15, 100),
                new Operation(30, 300),
                new Operation(25, 400)
        };
        int[] chainOfOperations = new int[]{0, 3, 4, 2, 2, 0, 1, 2, 1};
        int totalTime = 0;
        int totalCost = 0;
        int[] chainOfTemporary = new int[chainOfOperations.length - 1];
        int[] chainOfCosts = new int[chainOfOperations.length - 1];
        for (int i=0; i < (chainOfOperations.length - 1); i++){
            totalTime = totalTime + operations[chainOfOperations[i]].getExecutionTime();
            totalCost = totalCost + operations[chainOfOperations[i]].getExecutionCost();
            chainOfTemporary[i] = operations[chainOfOperations[i]].getExecutionTime();
            chainOfCosts[i] = operations[chainOfOperations[i]].getExecutionCost();
        }

        System.out.println("Общее время выполнения: " + totalTime);
        System.out.println("Общая стоимость выполнения: " + totalCost);
        System.out.println("Цепочка времени:" + Arrays.toString(chainOfTemporary));
        System.out.println("цепочка затрат:" + Arrays.toString(chainOfCosts));
        for (int i =  0; i < operations.length; i++) {
            System.out.println("Операция " + i + ": время - " + operations[i].getExecutionTime() + ", стоимость - " + operations[i].getExecutionCost());
        }

    }
}
