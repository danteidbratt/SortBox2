package controller;

import controller.Sorting.Algorithm;
import controller.Sorting.Magic;
import model.Data;
import model.Retreivable;
import unit.Resolution;
import unit.Setting;
import unit.Speed;
import unit.Theme;
import view.Gui;
import view.Window;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static java.awt.Font.BOLD;

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
                new Speed("Mid", 10),
                new Speed("High", 1),
                new Speed("Unlimited", 0)
        );
        resolutions = Arrays.asList(
                new Resolution("Low", 16),
                new Resolution("Mid", 64),
                new Resolution("High", 128),
                new Resolution("Extreme", 256)
        );
        theme = new Theme(new Color(220, 220, 0),
                Color.DARK_GRAY,
                new Color(50, 50, 50),
                Color.GREEN,
                Color.WHITE,
                new Font("SansSerif", BOLD, 25));
    }

    private void loadData() {
        data = new Data();
        data.loadValues(resolutions.get(0).getValue());
    }

    private void loadView() {
        window = new Gui(speeds, resolutions, theme);
        window.setShuffleListener(e -> {
            if(!Algorithm.isBusy()) {
                window.updateBars(data.shuffleValues());
            }
        });
        window.updateBars(data.getValues());
        window.setResolutionListener(e -> {
            if(!Algorithm.isBusy()) {
                data.loadValues(window.getResolution());
                window.updateBars(data.getValues());
            }
        });
    }

    private void loadAlgorithms() {
        Algorithm.setData(data);
        window.setAlgorithms(new Magic(window, data).loadAlgorithms());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

}