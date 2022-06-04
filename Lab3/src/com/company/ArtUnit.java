package com.company;

public abstract class ArtUnit implements InfoPrinting{
    private String name;
    private int genre;
    private int rating;

    public String getName(){
        return name;
    }

    public int getGenre(){
        return genre;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setGenre(int genre){
        this.genre = genre;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public abstract int getInfo();
}