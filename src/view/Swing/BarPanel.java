package view.Swing;

import java.awt.*;

public class BarPanel extends AbstractPanel {

    private Bar[] bars;

    BarPanel() {
        setBackground(theme.getInactiveColor());
        setPreferredSize(new Dimension(1024, 512));
    }

    void update(int[] values) {
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

    public void swap(int[] values, int first, int second) {
        bars[first].setValue(values[first]);
        bars[second].setValue(values[second]);
        revalidate();
    }

    public void scan(int index) {
        bars[index].scan();
    }

}