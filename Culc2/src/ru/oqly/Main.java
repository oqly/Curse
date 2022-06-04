package ru.oqly;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean status = false;

        String A = "";
        while (!status) {
            System.out.println("Введите исходную последовательность");
            A = scanner.nextLine();
            if (A.matches("[0-1]*")) {
                status = true;
            } else {
                System.out.println("Неправильный формат!");
            }
        }

        String[] strA = A.split("");
        int lengthA = strA.length;
        byte[] byteA = new byte[lengthA];
        Boolean[] Aint = new Boolean[lengthA];
        for (int i = 0; i < lengthA; i++){
            byteA[i] = Byte.parseByte(strA[i]);
            Aint[i] = byteA[i] != 0;
        }

        Boolean[] Bint = new Boolean[lengthA];
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < 3; i++){
            Bint[i] = Aint[i];
            strB.append((byte) (Bint[i] ? 1 : 0));
        }
        for (int i = 3; i < 5; i++){
            Bint[i] = (Aint[i]) ^  (Bint[i-3]);
            strB.append((byte) (Bint[i] ? 1 : 0));
        }
        for (int i = 5; i < lengthA; i++){
            Bint[i] = ((Aint[i]) ^ (Bint[i-3])) ^ (Bint[i-5]);
            strB.append((byte) (Bint[i] ? 1 : 0));
        }

        System.out.println("Закодированная последовательность: " + strB);

        Boolean[] Cbool = new Boolean[lengthA];
        StringBuilder strC = new StringBuilder();
        for (int i = 0; i < 3; i++){
            Cbool[i] = Bint[i];
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }
        for (int i = 3; i < 5; i++){
            Cbool[i] = (Bint[i]) ^  (Bint[i-3]);
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }
        for (int i = 5; i < lengthA; i++){
            Cbool[i] = ((Bint[i]) ^ (Bint[i-3])) ^ (Bint[i-5]);
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }

        System.out.println("Раскодированная последовательность: " + strC);

        System.out.println("\nСвой скремблер B[i] = A[i] + B[i-2] + B[i-6]");

        A = "";
        status = false;
        while (!status) {
            System.out.println("Введите исходную последовательность");
            A = scanner.nextLine();
            if (A.matches("[0-1]*")) {
                status = true;
            } else {
                System.out.println("Неправильный формат!");
            }
        }

        strA = A.split("");
        lengthA = strA.length;
        byteA = new byte[lengthA];
        Aint = new Boolean[lengthA];
        for (int i = 0; i < lengthA; i++){
            byteA[i] = Byte.parseByte(strA[i]);
            Aint[i] = byteA[i] != 0;
        }

        Bint = new Boolean[lengthA];
        strB = new StringBuilder();
        for (int i = 0; i < 2; i++){
            Bint[i] = Aint[i];
            strB.append((byte) (Bint[i] ? 1 : 0));
        }
        for (int i = 2; i < 6; i++){
            Bint[i] = (Aint[i]) ^  (Bint[i-2]);
            strB.append((byte) (Bint[i] ? 1 : 0));
        }
        for (int i = 6; i < lengthA; i++){
            Bint[i] = ((Aint[i]) ^ (Bint[i-2])) ^ (Bint[i-6]);
            strB.append((byte) (Bint[i] ? 1 : 0));
        }

        System.out.println("Закодированная последовательность: " + strB);

        Cbool = new Boolean[lengthA];
        strC = new StringBuilder();
        for (int i = 0; i < 2; i++){
            Cbool[i] = Bint[i];
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }
        for (int i = 2; i < 6; i++){
            Cbool[i] = (Bint[i]) ^  (Bint[i-2]);
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }
        for (int i = 6; i < lengthA; i++){
            Cbool[i] = ((Bint[i]) ^ (Bint[i-2])) ^ (Bint[i-6]);
            strC.append((byte) (Cbool[i] ? 1 : 0));
        }

        System.out.println("Раскодированная последовательность: " + strC);
    }
}