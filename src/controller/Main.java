package controller;

import controller.Sorting.Magic;
import model.Data;
import model.Retreivable;
import unit.*;
import view.Gui;
import view.Window;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    private Retreivable data;
    private Window window;
    private List<Setting> speeds;
    private List<Setting> resolutions;
    private Theme theme;

    private void start() {
        loadSettings();
        loadData();
        loadView();
        loadAlgorithms();
    }

    private void loadSettings() {
        speeds = Arrays.asList(
                new Speed("Low", 300),
                new Speed("Mid", 100),
                new Speed("High", 10),
                new Speed("Ultra", 1)
        );
        resolutions = Arrays.asList(
                new Resolution("Low", 16),
                new Resolution("Mid", 64),
                new Resolution("High", 128),
                new Resolution("Ultra", 256)
        );
        theme = new Theme(new Color(220, 220, 0), Color.DARK_GRAY, new Color(50, 50, 50), Color.GREEN, Color.WHITE);
    }

    private void loadData() {
        data = new Data();
        data.loadValues(resolutions.get(0).getValue());
    }

    private void loadView() {
        window = new Gui(speeds, resolutions, theme);
        window.setShuffleListener(e -> window.updateBars(data.shuffleValues()));
        window.updateBars(data.getValues());
        window.setResolutionListener(e -> {
            data.loadValues(window.getResolution());
            window.updateBars(data.getValues());
        });
    }

    private void loadAlgorithms() {
        window.setAlgorithms(new Magic(window, data).loadAlgorithms());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

}