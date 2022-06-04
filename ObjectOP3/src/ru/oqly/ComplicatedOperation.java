package ru.oqly;

public class ComplicatedOperation extends Operation {
    private int costop;

    public ComplicatedOperation(int number, int timeop, int costop){
        setTimeop(timeop);
        setNumber(number);
        this.costop = costop;
    }

    @Override
    public int getTimeop() {
        return timeop;
    }

    @Override
    public int getCostop() {
        return costop;
    }

    @Override
    public String getInfo(int repeats) {
        return ("Сложная операция " + getNumber() + ": общее время - " + repeats * getTimeop() + ", общая стоимость - " + repeats * costop + ", Количество повторений - " + repeats + '\n');
    }
}
