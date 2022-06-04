package com.company;

public abstract class Operation  implements InfoGiver{
    protected   int executionTime;
    protected   int Number;

    public int getExecutionTime(){
        return executionTime;
    }

    public int getNumber() {
        return Number;
    }

    public void setExecutionTime(int Time){
        executionTime = Time;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public abstract int getCost();
}
