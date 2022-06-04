package com.company;

public class Actor implements InfoPrinting{
    private String name;
    private int age;

    public String getPplName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String getFullInfo(){
    return ("Актёр: " + getPplName() + ", его возраст: " + getAge() + "\n");
    }

    @Override
    public String getPartialInfo(){
        return ("Актёр: " + getPplName());
    };

    public Actor(String name, int age){
        this.name = name;
        this.age = age;
    }
}
