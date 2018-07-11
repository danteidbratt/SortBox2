package view;

public interface Sortable {

    void scan(int index);

    void swapPair(int[] data, int first, int second);

    void startTimer();

    void stopTimer();

    void resetTimer();

}