package view.Swing;

import controller.Sorting.Algorithm;
import unit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.BorderLayout.*;
import static javax.swing.JFrame.*;

public final class SwingFrame implements view.Window {

    private JFrame frame;
    private JPanel mainPanel;
    private BarPanel barPanel;
    private MenuPanel menuPanel;
    private SettingsPanel settingsPanel;

    public SwingFrame(List<Setting> speeds, List<Setting> resolutions, Theme theme) {
        setTheme(theme);
        setSpeed(speeds.get(0));
        frame = new JFrame();
        frame.setTitle("SortBox");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        LogoPanel logoPanel = new LogoPanel();
        mainPanel.add(logoPanel, NORTH);

        barPanel = new BarPanel();
        mainPanel.add(barPanel, CENTER);

        menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, EAST);

        settingsPanel = new SettingsPanel(resolutions, speeds, e -> Bar.setScanningDuration(settingsPanel.getSpeed()));
        mainPanel.add(settingsPanel, SOUTH);
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
        return settingsPanel.getResolution();
    }

    @Override
    public void setResolutionListener(ActionListener resolutionListener) {
        settingsPanel.setResolutionListener(resolutionListener);
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