package com.company;

public class Serial extends ArtUnit implements InfoPrinting{

    @Override
    public int getInfo(){
        return getRating();
    }

    @Override
    public String getFullInfo(){
        return ("Сериал: " + getName() + ", рейтинг: " + getInfo() + ", жанр: " + getGenre() + "\n");
    }

    @Override
    public String getPartialInfo(){
        return ("Сериал: " + getName() + ", рейтинг: " + getRating() + "\n");
    };

    public Serial(String name, int rating, int genre) {
        setName(name);
        setGenre(genre);
        setRating(rating);
    }

}