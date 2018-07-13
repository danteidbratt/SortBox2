package view;

public interface Display {

    void scan(int index);

    void swapPair(int firstValue, int secondValue, int firstIndex, int secondIndex);

    void updateSequence(int[] sequence, int first);

    void startTimer();

    void stopTimer();

    void resetTimer();

}