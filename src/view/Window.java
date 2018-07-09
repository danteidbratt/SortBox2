package view;

import controller.Sorting.Algorithm;
import unit.Resolution;

import java.awt.event.ActionListener;
import java.util.List;

public interface Window extends Sortable {

    void updateBars(int[] values);

    Resolution getResolution();

    void setResolutionListener(ActionListener actionListener);

    void setShuffleListener(ActionListener actionListener);

    void setAlgorithms(List<Algorithm> algorithms);

}