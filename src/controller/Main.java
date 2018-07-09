package controller;

import controller.Sorting.Algorithm;
import controller.Sorting.Setup;
import model.Data;
import model.Retreivable;
import unit.*;
import view.Gui;
import view.Window;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    List<Algorithm> algorithms;
    private final Retreivable data;
    private final Window window;

    public Main() {
        List<Speed> speeds = Arrays.asList(
                new Speed("Low", 300),
                new Speed("Mid", 100),
                new Speed("High", 10),
                new Speed("Ultra", 1)
        );
        List<Resolution> resolutions = Arrays.asList(
                new Resolution("Low", 16),
                new Resolution("Mid", 64),
                new Resolution("High", 128),
                new Resolution("Ultra", 256)
        );
        Theme theme = new Theme(Color.YELLOW, Color.DARK_GRAY, new Color(50, 50, 50), Color.GREEN);
        data = new Data();
        data.loadValues(resolutions.get(0).getValue());
        window = new Gui(speeds, resolutions, theme);
        algorithms = new Setup(window, data).getSortingAlgorithms();

        window.setAlgorithms(algorithms);
        window.setShuffleListener(e -> window.updateBars(data.shuffleValues()));
        window.updateBars(data.getValues());
    }

    private void start() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}