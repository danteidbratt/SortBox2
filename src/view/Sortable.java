package view;

public interface Sortable {

    void scan(int index);

    void swapPair(int[] data, int first, int second);

    void updateSequence(int[] sequence, int first);

    void startTimer();

    void stopTimer();

    void resetTimer();

}