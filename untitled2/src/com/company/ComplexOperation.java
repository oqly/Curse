package com.company;

public class ComplexOperation extends Operation{
    private  int executionCost;

    public ComplexOperation(int number, int executionTime, int executionCost){
        this.executionCost = executionCost;
        setExecutionTime(executionTime);
        setNumber(number);
    }

    @Override
    public int getCost() {
        return executionCost;
    }

    @Override
    public String getInfo() {
        return ("Комплексная операция " + getNumber() + ": Время - " + getExecutionTime() + " Стоимость - " + getCost() + "\n");
    }
}
