package ru.oqly;

public class Main {

    public static void main(String[] args) {

        int[] chainop = new int[]{0, 3, 4, 2, 2, 0, 1, 2, 1, 7, 5, 6, 5, 7}; //цепочка операций
        Operation[] operations = new Operation[]{
                new ComplicatedOperation(1,10, 100),
                new ComplicatedOperation(2,5, 20),
                new ComplicatedOperation(3,15, 100),
                new ComplicatedOperation(4,30, 300),
                new ComplicatedOperation(5,25, 400),
                new SimpleOperation(1,20),
                new SimpleOperation(2,10),
                new SimpleOperation(3,15)
        };
        int countop = operations.length; //количество операций
        int sizeop = chainop.length; //количество элементов цепочки операций
        int[] repop = new int[countop]; //количество повторений

        String inputstr = "Входные данные: \n";
        for (int j = 0; j < countop; j++) {
            inputstr += "Операция " + j + ": время - " + operations[j].getTimeop() + ", стоимость - " + operations[j].getCostop() + '\n';
        }
        System.out.println(inputstr);

        int sumtime = 0;
        int sumcost = 0;
        String chainTime = "Цепочка времени: ";
        String chainCost = "Цепочка затрат: ";
        String strsum = "Общие затраты: \n";

        for (int i = 0; i < sizeop; i++) {
            sumtime += operations[chainop[i]].getTimeop();
            sumcost += operations[chainop[i]].getCostop();
            repop[chainop[i]]++;
            chainTime += operations[chainop[i]].getTimeop() + " ";
            chainCost += operations[chainop[i]].getCostop() + " ";
        }

        for (int j = 0; j < countop; j++) {
            strsum += operations[j].getInfo(repop[j]);
        }

        System.out.println("Суммарное время: " + sumtime + '\n' + "Суммарные затраты: " + sumcost + '\n' + chainTime + '\n' + chainCost + '\n' + strsum);
    }
}