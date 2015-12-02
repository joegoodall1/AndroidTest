package com.candyspace.androidtest;

import java.io.IOException;

public class Bar {
    private final ComplicatedThingy complicatedThingy;

    public Bar(ComplicatedThingy complicatedThingy) {
        this.complicatedThingy = complicatedThingy;
    }

    public void doSomethingComplicated() {
        complicatedThingy.doSomething();
    }

    public int doSomethingElse() {
        //loads of code here
        int amount = 99;

        try {
            final int result = complicatedThingy.doSomethingElse(amount);
            return result / 2;
        } catch (IOException e) {
            throw new IllegalStateException("This should never happen", e);
        }
    }

    public int count() {
        return -99;
    }

    public String reverse(String text) {
        return text;
    }

    public boolean isStringGreaterThanZero(String aNumber) {
        if (!aNumber.matches("\\d+"))
            throw new IllegalStateException("Input '" + aNumber + "' is not an integer");

        final int num = Integer.valueOf(aNumber);
        return num > 0;
    }

    public boolean isStringGreaterThanZero2(String aNumber) throws BadInputException {
        if (!aNumber.matches("\\d+"))
            throw new BadInputException("Input '" + aNumber + "' is not an integer");

        final int num = Integer.valueOf(aNumber);
        return num > 0;
    }

    public static class BadInputException extends Exception {
        public BadInputException(String message) {
            super(message);
        }
    }
}
