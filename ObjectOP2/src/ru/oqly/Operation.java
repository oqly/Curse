package ru.oqly;

public abstract class Operation implements GetInfoOfOperations {
    protected int timeop;
    protected int number;

    public int getTimeop() {
        return timeop;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTimeop(int timeop) {
        this.timeop = timeop;
    }

    public abstract int getCostop();
}