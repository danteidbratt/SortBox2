package model;

public interface Sortable {

    int[] getValues();

    int[] getValues(int from, int to);

    int getValue(int index);

    int[] orderSlope(int numberOfValues);

    int[] orderStairs(int numberOfValues);

    int[] shuffleOrder();

    int[] reverseOrder();

    int[] missplaceOne();

    void swapValues(int first, int second);

    void insertSequence(int startIndex, int[] values);

    int getSize();

    boolean isShuffled();
}
