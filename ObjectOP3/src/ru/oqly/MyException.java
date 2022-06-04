package ru.oqly;

public class MyException extends Throwable {

    private final int lower;
    private final int upper;

    public MyException(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public int getLower() { return lower; }

    public int getUpper() { return upper; }
}