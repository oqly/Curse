package ru.oqly;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean status = false;
        boolean status2 = false;
        boolean statusmask = false;
        String scip = "";
        Integer[] ip = new Integer[4];

        while (!status2) {
            while (!status) {
                System.out.println("Введите IP");
                scip = scanner.nextLine();
                if (scip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    status = true;
                } else {
                    System.out.println("Неправильный формат строки!");
                }
            }
            String[] str = scip.split("[.]");

            status2 = true;
            for (int i = 0; i < 4; i++) {
                ip[i] = Integer.parseInt(str[i]);
                if (ip[i] > 255) {
                    status2 = false;
                    status = false;
                    System.out.println("Ошибка в байте №" + (i+1) + "!");
                }
            }
        }
        int koled = 0;
        String stringmask = "";

        while (!statusmask) {
            System.out.println("Введите количество единиц маски");
            stringmask = scanner.nextLine();
            if (stringmask.matches("\\d{1,2}")) {
                statusmask = true;
                if (Integer.parseInt(stringmask) > 32) {
                    statusmask = false;
                    System.out.println("Количество единиц в маске не может превышать 32!");
                }
            } else {
                System.out.println("Неправильный формат маски!");
            }
        }
        koled = Integer.parseInt(stringmask);

        boolean gg = (koled == 32);
        long hosts = (long)Math.pow(2, 32-koled);
        Integer [] mask = {0, 0, 0, 0};

        for (int i = 0; i<4; i++) {
            if (koled >= 8){
                mask[i] = 255;
                koled -= 8;
            }
            else {
                for (int j = 0; j<koled; j++){
                    mask[i] += (int)Math.pow(2, 7-j);
                }
                break;
            }
        }

        System.out.println("Mask: " + mask[0] + "." + mask[1] + "." + mask[2] + "." + mask[3]);

        Integer [] Adress = {0, 0, 0, 0};
        Long [] Broadcast = {0L, 0L, 0L, 0L};
        long qq = 0;
        int okt = 0;
        for (int i = 0; i<4; i++){
            if (mask[i] == 255){
                Adress[i] = ip[i];
                Broadcast[i] = (long)ip[i];
            }
            else {
                qq = (hosts / (long)Math.pow(2, 8*(3-i)))-1;
                okt = i;
                break;
            }
        }

        long adressOkt = ip[okt] / (qq+1);
        Adress[okt] = Math.toIntExact(adressOkt * (qq+1));
        Broadcast[okt] = Adress[okt]+qq;

        for (int i = okt+1; i <= 3; i++){
            Broadcast[i] = 255L;
        }

        System.out.println("Network: " + Adress[0] + "." + Adress[1] + "." + Adress[2] + "." + Adress[3]);

        if (!gg){
            System.out.println("Broadcast: " + Broadcast[0] + "." + Broadcast[1] + "." + Broadcast[2] + "." + Broadcast[3]);
            System.out.println("Количество доступных хостов: " + hosts + " (из них " + (hosts-2) + " рабочих адресов для хостов)");
        }
        else {
            System.out.println(("Broadcast = N/A"));
        }
    }
}