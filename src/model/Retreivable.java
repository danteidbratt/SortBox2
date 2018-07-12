package model;

public interface Retreivable {

    int[] getValues();

    int[] orderSlope(int numberOfValues);

    int[] orderStairs(int numberOfValues);

    int[] shuffleOrder();

    int[] reverseOrder();

    int[] missplaceOne();

    void swapValues(int first, int second);

    int getSize();

    boolean isShuffled();
}
