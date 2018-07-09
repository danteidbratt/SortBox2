package view.Swing;

import controller.Sorting.Algorithm;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

final class SortingButton extends JButton {

    SortingButton(Algorithm sortingAlgorithm) {
        super(sortingAlgorithm.getIdentifier());
        addActionListener(sortingAlgorithm.getActionListener());
        setFont(new Font("SansSerif", BOLD, 25));
    }

}