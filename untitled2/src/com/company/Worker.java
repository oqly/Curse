package com.company;

public class Worker implements InfoGiver{
    private String name;
    private String subName;
    private int brigadeNumber;

    public String getName() {
        return name;
    }
    public String getSubName() {
        return subName;
    }
    public int getBrigadeNumber() {
        return brigadeNumber;
    }

    @Override
    public String getInfo() {
        return ("Рабочий: " + getName() + " " + getSubName() + ". Номер Бригады - " + getBrigadeNumber() + "\n");
    }

    public Worker(String name,String subName, int brigadeNumber){
        this.name = name;
        this.subName = subName;
        this.brigadeNumber = brigadeNumber;
    }
}
