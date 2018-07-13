package view;

import controller.Sorting.Algorithm;
import unit.Setting;
import unit.Theme;
import view.Swing.SwingFrame;

import java.awt.event.ActionListener;
import java.util.List;

public final class Gui implements Window {

    private Window frame;

    public Gui(List<Setting> shuffleTypes, List<Setting> speeds, List<Setting> resolutions, List<Setting> increments, Theme theme) {
        frame = new SwingFrame(shuffleTypes, speeds, resolutions, increments, theme);
    }

    @Override
    public void updateBars(int[] values) {
        frame.updateBars(values);
    }

    @Override
    public int getResolution() {
        return frame.getResolution();
    }

    @Override
    public int getShuffleType() {
        return frame.getShuffleType();
    }

    @Override
    public int getIncrements() {
        return frame.getIncrements();
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
    public void setIncrementListener(ActionListener actionListener) {
        frame.setIncrementListener(actionListener);
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
    public void updateSequence(int[] sequence, int firstIndex) {
        frame.updateSequence(sequence, firstIndex);
    }

    @Override
    public void startTimer() {
        frame.startTimer();
    }

    @Override
    public void stopTimer() {
        frame.stopTimer();
    }

    @Override
    public void resetTimer() {
        frame.resetTimer();
    }
}