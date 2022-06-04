package com.company;

public class Operation {
    int executionTime;
    int executionCost;
    public int getExecutionTime(){
        return executionTime;
    }
    public int getExecutionCost(){
        return executionCost;
    }
    public Operation(int executionTime, int executionCost){
        this.executionCost = executionCost;
        this.executionTime = executionTime;
    }
}
