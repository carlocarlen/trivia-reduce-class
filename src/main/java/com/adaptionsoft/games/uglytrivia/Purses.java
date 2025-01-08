package com.adaptionsoft.games.uglytrivia;

public class Purses {

    private final int[] purses  = new int[6];

    public int getPurse(int index) {
        return purses[index];
    }

    public void setPurse(int index, int value) {
        purses[index] = value;
    }

    public void increment(int index) {
        purses[index]++;
    }

}
