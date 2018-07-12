package view;

import controller.Sorting.Algorithm;

import java.awt.event.ActionListener;
import java.util.List;

public interface Window extends Sortable {

    void updateBars(int[] values);

    int getResolution();

    int getShuffleType();

    int getIncrements();

    void setResolutionListener(ActionListener actionListener);

    void setShuffleListener(ActionListener actionListener);

    void setIncrementListener(ActionListener actionListener);

    void setAlgorithms(List<Algorithm> algorithms);

}