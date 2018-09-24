package com.example.anass.higher_lower;

public class Dice {

    private final int MIN_DICE = 1;
    private final int MAX_DICE = 6;
    public int currentRoll;

    public int getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(int currentRoll) {
        this.currentRoll = currentRoll;
    }

    public Dice() {}

    public void diceRoll(){
        setCurrentRoll((int)(Math.random() * MAX_DICE) + MIN_DICE);
    }
}
