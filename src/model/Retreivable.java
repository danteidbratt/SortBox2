package model;

public interface Retreivable {

    void loadValues(int numberOfValues);

    int[] getValues();

    int[] shuffleValues();

    void swapValues(int first, int second);

    int getSize();

    boolean isShuffled();
}
