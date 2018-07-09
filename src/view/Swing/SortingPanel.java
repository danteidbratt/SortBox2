package view.Swing;

import controller.Sorting.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class SortingPanel extends AbstractPanel {

    SortingPanel(List<Algorithm> sortingAlgorithms) {
        List<SortingButton> sortingButtons = new ArrayList<>();
        for (Algorithm s : sortingAlgorithms) {
            sortingButtons.add(new SortingButton(s));
        }
        setLayout(new GridLayout(sortingButtons.size(), 1));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        setBackground(theme.getBackgroundColor());
        for (SortingButton s : sortingButtons) {
            add(s);
        }
        setPreferredSize(new Dimension(250, 500));
    }

}