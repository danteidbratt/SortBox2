package view.Swing;

import java.awt.*;

final class BarPanel extends AbstractPanel {

    private Bar[] bars;

    BarPanel() {
        setBackground(theme.getInactiveColor());
        setPreferredSize(new Dimension(1024, 512));
    }

    void updateAll(int[] values) {
        removeAll();
        Bar.setResolution(values.length);
        bars = new Bar[values.length];
        setLayout(new GridLayout(1, bars.length + 1));
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar();
            bars[i].setValue(values[i]);
            add(bars[i]);
        }
        revalidate();
    }

    void updateSequence(int[] sequence, int firstIndex) {
        for (int i = 0; i < sequence.length; i++) {
            bars[i + firstIndex].setValue(sequence[i]);
        }
        revalidate();
    }

    void swap(int firstValue, int secondValue, int first, int second) {
        bars[first].setValue(firstValue);
        bars[second].setValue(secondValue);
        revalidate();
    }

    void scan(int index) {
        bars[index].scan();
    }

}