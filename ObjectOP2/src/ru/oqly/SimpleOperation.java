package ru.oqly;

public class SimpleOperation extends Operation {

    public SimpleOperation(int number, int timeop){
        setTimeop(timeop);
        setNumber(number);
    }

    @Override
    public int getCostop() {
        return 0;
    }

    @Override
    public String getInfo(int repeats) {
        return ("Простая операция " + getNumber() + ": общее время - " + repeats * getTimeop() + ", Количество повторений - " + repeats + '\n');
    }
}

