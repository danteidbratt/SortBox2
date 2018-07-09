package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Data implements Retreivable{

    private int[] values;

    public void loadValues(int numberOfValues) {
        this.values = new int[numberOfValues];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
    }

    public int[] getValues() {
        return values;
    }

    public int[] shuffleValues() {
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

    public synchronized void swapValues(int first, int second) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }

    public int getSize() {
        return values.length;
    }
}