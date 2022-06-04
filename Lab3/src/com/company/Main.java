package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        StringBuilder processedList = new StringBuilder();
        StringBuilder matchedList = new StringBuilder();
        List<ArtUnit> ArtUnits = new ArrayList<>();
        List<InfoPrinting> Arts;

        ArtUnits.add(new Film("Bloodshot", 3, 1));
        ArtUnits.add(new Film("Venom", 6, 4));
        ArtUnits.add(new Film("Spider-Man", 7, 2));
        ArtUnits.add(new Film("Venom 2", 6, 4));
        ArtUnits.add(new Film("Sonic", 1, 8));
        ArtUnits.add(new Serial("Naruto", 4, 1));
        ArtUnits.add(new Serial("Boruto", 3, 8));
        ArtUnits.add(new Serial("Attack on Titan", 8, 9));
        ArtUnits.add(new Serial("Bakugan", 5, 17));
        ArtUnits.add(new Serial("Brigada", 5, 9));

        List<Actor> Actors = new ArrayList<>();
        Actors.add(new Actor("Al Pacino", 81));
        Actors.add(new Actor("Tom Hanks", 65));
        Actors.add(new Actor("Clint Eastwood", 91));
        Actors.add(new Actor("Johnny Depp", 58));
        Actors.add(new Actor("Morgan Freeman", 84));

        Arts = new ArrayList<>();
        Arts.addAll(ArtUnits);
        Arts.addAll(Actors);

        int maxRating = -1;
        int minRating = 9999999;
//        double avgRating = 0;
        String maxRated = "";
        String minRated = "";

//        int sameGenreSum = 1;
//        int filmCount = 0;

//        double [] avgFilmGenreRating = new double[20];

        for (InfoPrinting Art : Arts) {
            output.append(Art.getFullInfo());
        }

//        System.out.println("===========================================================");
        System.out.println("Обработка входной коллекции (оценки от 3 до 5, лимит - 3):" + "\n");

        DataProcessor<ArtUnit> ArtUnitStream = new DataProcessor<>();
        ArtUnitStream.setList(ArtUnits);

        try {
            List<ArtUnit> processed = new ArrayList<>(ArtUnitStream.processing(9, 5));
            for (InfoPrinting element : processed) {
                processedList.append(element.getPartialInfo());
            }
            System.out.println(processedList);
        } catch (myException e) {
            System.out.println("Нижняя граница (" + e.getLower() + ") не может быть больше верхней ("
            + ArtUnitStream.upperRangeValue + ") !");
        }


        System.out.println("""

                Поиск по оценке (7), лимит - 1:
                """);

        List<ArtUnit> matched = new ArrayList<>();
        matched.add(ArtUnitStream.matching(1));

        try {
            for (InfoPrinting element : matched) {
                if (element != null) {
                    matchedList.append(element.getPartialInfo());
                }
            }
        } catch (NullPointerException e){
            System.out.println("Элемент не найден!");
        }
        System.out.println(matchedList);
//        System.out.println("===========================================================");

        for (ArtUnit artUnit : ArtUnits) {
            if (artUnit.getRating() > maxRating) {
                maxRating = artUnit.getRating();
                maxRated = artUnit.getPartialInfo();
            }
            if (artUnit.getRating() < minRating) {
                minRating = artUnit.getRating();
                minRated = artUnit.getPartialInfo();
            }


//            avgFilmGenreRating[i] += ArtUnits.get(i).getInfo();
//            filmCount += 1;


//            int currentGenre = ArtUnits.get(i).getGenre();
//            for (int j = i + 1; j < ArtUnits.size(); j++) {
//                if (currentGenre == ArtUnits.get(i).getGenre()) {
//                    sameGenreSum++;
//                    avgFilmGenreRating[i] += ArtUnits.get(j).getInfo();
//                    ArtUnits.get(j).setGenre(-1);
//                }
//            }

//            avgFilmGenreRating[i] /= sameGenreSum;
//            avgRating += artUnit.getInfo();

//            if (ArtUnits.get(i).getGenre() != -1) {
//                output.append("Жанр ").append(ArtUnits.get(i).getGenre())
//                        .append(": фильмов и сериалов просмотрено ")
//                        .append(sameGenreSum)
//                        .append(", средняя оценка по жанру ")
//                        .append(avgFilmGenreRating[i]).append("\n");
//            }

//            sameGenreSum = 1;
        }

        System.out.println(output);
//        System.out.println("Средняя оценка фильмов и сериалов: " + avgRating / filmCount);
        System.out.println("Максимальная оценка: \n" + maxRated + "\n");
        System.out.println("Минимальная оценка: \n" + minRated);

    }
}
