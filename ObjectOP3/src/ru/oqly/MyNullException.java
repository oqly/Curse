package ru.oqly;

public class MyNullException extends Throwable {

    private final int match;

    public MyNullException(int match){
        this.match = match;
    }

    public int getMatch() { return match; }
}
