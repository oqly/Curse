package com.company;

public class SimpleOperation extends Operation{

    public SimpleOperation(int number, int executionTime){
        setNumber(number);
        setExecutionTime(executionTime);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getInfo() {
        return ("Простая операция " + getNumber() + ": Время - " + getExecutionTime() + "\n");
    }
}
