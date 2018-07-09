package view.Swing;

import javax.swing.*;
import java.awt.*;

public final class Bar extends AbstractPanel {

    private static int increment;
    private static int scanningDuration;
    private final JLabel stick;

    Bar() {
        setBackground(theme.getInactiveColor());
        stick = new JLabel();
        stick.setBackground(theme.getActiveColor());
        stick.setOpaque(true);
        setLayout(new GridLayout(1, 1));
        add(stick);
    }

    public void setValue(int value) {
        setBorder(BorderFactory.createEmptyBorder(512 - (increment * (value + 1)), 0, 0, 0));
    }

    public static void setResolution(int resolution) {
        increment = 512 / resolution;
    }

    public static void setScanningDuration(int value) {
        scanningDuration = value;
    }

    public void scan() {
        stick.setBackground(theme.getScanningColor());
        repaint();
        rest();
        stick.setBackground(theme.getActiveColor());
        repaint();
    }

    private void rest() {
        try {
            Thread.sleep(scanningDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}