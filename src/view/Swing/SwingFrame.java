package view.Swing;

import controller.Sorting.Algorithm;
import unit.Setting;
import unit.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.BorderLayout.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public final class SwingFrame implements view.Window {

    private JFrame frame;
    private TimePanel timePanel;
    private JPanel mainPanel;
    private BarPanel barPanel;
    private Dashboard dashboard;

    public SwingFrame(List<Setting> shuffleTypes, List<Setting> speeds, List<Setting> resolutions, List<Setting> increments, Theme theme) {
        setTheme(theme);
        setSpeed(speeds.get(0));
        frame = new JFrame();
        frame.setTitle("SortBox");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(theme.getBackgroundColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        frame.add(mainPanel);

        timePanel = new TimePanel();
        LogoPanel logoPanel = new LogoPanel(timePanel);
        mainPanel.add(logoPanel, NORTH);

        barPanel = new BarPanel();
        mainPanel.add(barPanel, CENTER);

        dashboard = new Dashboard(shuffleTypes, resolutions, speeds, increments, e -> Bar.setScanningDuration(dashboard.getSpeed()));
        mainPanel.add(dashboard, SOUTH);
        frame.pack();
    }

    private void setTheme(Theme theme) {
        AbstractPanel.setTheme(theme);
    }

    private void setSpeed(Setting speed) {
        Bar.setScanningDuration(speed.getValue());
    }

    @Override
    public void updateBars(int[] values) {
        barPanel.update(values);
    }

    @Override
    public int getResolution() {
        return dashboard.getResolution();
    }

    @Override
    public int getShuffleType() {
        return dashboard.getShuffleType();
    }

    @Override
    public int getIncrements() {
        return dashboard.getIncrements();
    }

    @Override
    public void setResolutionListener(ActionListener resolutionListener) {
        dashboard.setResolutionListener(resolutionListener);
    }

    @Override
    public void setShuffleListener(ActionListener shuffleListener) {
        dashboard.setShuffleListener(shuffleListener);
    }

    @Override
    public void setIncrementListener(ActionListener actionListener) {
        dashboard.setIncrementListener(actionListener);
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
        timePanel.start();
    }

    @Override
    public void stopTimer() {
        timePanel.stop();
    }

    @Override
    public void resetTimer() {
        timePanel.reset();
    }

}