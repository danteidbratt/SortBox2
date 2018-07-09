package view.Swing;

import controller.Sorting.Algorithm;
import unit.*;
import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.BorderLayout.*;
import static javax.swing.JFrame.*;

public class SwingFrame extends AbstractFrame {

    private JFrame frame;
    private JPanel mainPanel;
    private BarPanel barPanel;
    private MenuPanel menuPanel;

    public SwingFrame(List<Speed> speeds, List<Resolution> resolutions, Theme theme) {
        super(speeds, resolutions);
        setTheme(theme);
        setSpeed(speeds.get(0));
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        barPanel = new BarPanel();
        mainPanel.add(barPanel, CENTER);

        menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, EAST);
        frame.pack();
    }

    private void setTheme(Theme theme) {
        AbstractPanel.setTheme(theme);
    }

    private void setSpeed(Speed speed) {
        Bar.setScanningDuration(speed.getValue());
    }

    @Override
    public void updateBars(int[] values) {
        barPanel.update(values);
    }

    @Override
    public Resolution getResolution() {
        return null;
    }

    @Override
    public void setResolutionListener(ActionListener actionListener) {

    }

    @Override
    public void setShuffleListener(ActionListener shuffleListener) {
        menuPanel.setShuffleListener(shuffleListener);
    }

    @Override
    public void setAlgorithms(List<Algorithm> algorithms) {
        mainPanel.add(new SortingPanel(algorithms), WEST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void scan(int index) {
        barPanel.scan(index);
    }

    @Override
    public void swapPair(int[] data, int first, int second) {
        barPanel.swap(data, first, second);
    }

    @Override
    public void startTimer() {

    }

    @Override
    public void stopTimer() {

    }

}