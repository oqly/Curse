package com.company;

public class Film extends ArtUnit implements InfoPrinting{

    @Override
    public int getInfo(){
        return getRating();
    }

    @Override
    public String getFullInfo(){
        return ("Фильм: " + getName() + ", рейтинг: " + getInfo() + ", жанр: " + getGenre() + "\n");
    }

    @Override
    public String getPartialInfo(){
        return ("Фильм: " + getName() + ", рейтинг: " + getRating() + "\n");
    };

    public Film(String name, int rating, int genre) {
        setName(name);
        setGenre(genre);
        setRating(rating);
    }
}