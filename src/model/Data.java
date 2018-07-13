package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Data implements Sortable {

    private int[] values;

    public int[] getValues() {
        return values;
    }

    @Override
    public int[] getValues(int from, int to) {
        return Arrays.copyOfRange(values, from, to);
    }

    @Override
    public int getValue(int index) {
        return values[index];
    }

    @Override
    public int[] orderSlope(int numberOfValues) {
        this.values = new int[numberOfValues];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        return values;
    }

    @Override
    public int[] orderStairs(int numberOfValues) {
        values = new int[numberOfValues];
        for (int i = 1; i <= 4; i++) {
            generateStep(i);
        }
        return values;
    }

    private void generateStep(int divisor) {
        int increment = values.length / 4;
        int stop = divisor * increment;
        for (int i = stop - increment; i < stop; i++) {
            values[i] = (increment * divisor - 1);
        }
    }

    public int[] shuffleOrder() {
        List<Integer> temp = new ArrayList<>();
        for (int i : values) {
            temp.add(i);
        }
        Collections.shuffle(temp);
        for (int i = 0; i < values.length; i++) {
            values[i] = temp.get(i);
        }
        return values;
    }

    @Override
    public int[] reverseOrder() {
        int temp;
        for (int i = 0; i < values.length / 2; i++) {
            temp = values[i];
            values[i] = values[values.length - i - 1];
            values[values.length - i - 1] = temp;
        }
        return values;
    }

    @Override
    public int[] missplaceOne() {
        int missplaced = (int) ((Math.random() * values.length) * (0.75));
        for (int i = missplaced; i < values.length - 1; i++) {
            swapValues(i, i + 1);
        }
        return values;
    }

    public synchronized void swapValues(int first, int second) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }

    @Override
    public void insertSequence(int startIndex, int[] values) {
        System.arraycopy(values, 0, this.values, startIndex, values.length);
    }

    public int getSize() {
        return values.length;
    }

    @Override
    public boolean isShuffled() {
        for (int i = 0; i < values.length - 1; i++) {
            if(values[i] > values[i + 1]) {
                return true;
            }
        }
        return false;
    }
}