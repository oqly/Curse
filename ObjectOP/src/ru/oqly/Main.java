package ru.oqly;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество операций");
        int countop = scanner.nextInt();

        int[] timeop = new int[countop];
        int[] costop = new int[countop];
        int[] repop = new int[countop];

        System.out.println("Введите время выполнения");
        for (int i = 0; i<countop; i++) {
            timeop[i] = scanner.nextInt();
        }

        System.out.println("Введите стоимость выполнения");
        for (int i = 0; i<countop; i++) {
            costop[i] = scanner.nextInt();
        }

        System.out.println("Введите количество элементов цепочки операций");
        int sizeop = scanner.nextInt();

        int[] chainop = new int[sizeop];

        System.out.println("Введите цепочку операций");
        for (int i = 0; i<sizeop; i++) {
            chainop[i] = scanner.nextInt();
            repop[chainop[i]]++;
        }

        int sumtime = 0;
        int sumcost = 0;
        for (int i = 0; i< sizeop; i++) {
            sumtime += timeop[chainop[i]];
            sumcost += costop[chainop[i]];
        }
        System.out.println("Суммарное время: " + sumtime + '\n' + "Суммарные затраты: " + sumcost);

        String chainTime = "Цепочка времени: ";
        for (int i = 0; i< sizeop; i++) {
            chainTime += timeop[chainop[i]] + " ";
        }

        String chainCost = "Цепочка затрат: ";
        for (int i = 0; i< sizeop; i++) {
            chainCost += costop[chainop[i]] + " ";
        }

        String strsum = "";
        for (int j = 0; j< countop; j++) {
            strsum += "Операция " + j + ": время - " + repop[j]*timeop[j] + ", стоимость - " + repop[j]*costop[j] + '\n';
        }

        System.out.println(chainTime + '\n' + chainCost + '\n' + strsum);
    }
}