package com.company;

public class myException extends Throwable {

    private final int lower;

    public myException(int lower) {
        this.lower = lower;
    }

    public int getLower() {
        return lower;
    }
}
