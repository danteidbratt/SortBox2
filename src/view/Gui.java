package view;

import controller.Sorting.Algorithm;
import unit.*;
import view.Swing.SwingFrame;

import java.awt.event.ActionListener;
import java.util.List;

public class Gui implements Window {

    private AbstractFrame frame;

    public Gui(List<Speed> speeds, List<Resolution> resolutions, Theme theme) {
        frame = new SwingFrame(speeds, resolutions, theme);
    }

    @Override
    public void updateBars(int[] values) {
        frame.updateBars(values);
    }

    @Override
    public Resolution getResolution() {
        return frame.getResolution();
    }

    @Override
    public void setResolutionListener(ActionListener actionListener) {
        frame.setResolutionListener(actionListener);
    }

    @Override
    public void setShuffleListener(ActionListener actionListener) {
        frame.setShuffleListener(actionListener);
    }

    @Override
    public void setAlgorithms(List<Algorithm> algorithms) {
        frame.setAlgorithms(algorithms);
    }

    @Override
    public void scan(int index) {
        frame.scan(index);
    }

    @Override
    public void swapPair(int[] data, int first, int second) {
        frame.swapPair(data, first, second);
    }

    @Override
    public void startTimer() {
        frame.startTimer();
    }

    @Override
    public void stopTimer() {
        frame.stopTimer();
    }
}