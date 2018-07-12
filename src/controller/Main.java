package controller;

import controller.Sorting.Algorithm;
import controller.Sorting.Controller;
import model.Data;
import model.Retreivable;
import unit.*;
import view.Gui;
import view.Window;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static java.awt.Font.BOLD;

public class Main {

    private Retreivable data;
    private Window window;
    private List<Setting> mixes;
    private List<Setting> speeds;
    private List<Setting> resolutions;
    private List<Setting> increments;
    private Theme theme;

    private void start() {
        loadSettings();
        loadData();
        loadView();
        loadAlgorithms();
    }

    private void loadSettings() {
        mixes = Arrays.asList(
                new ShuffleType("Random", 0),
                new ShuffleType("Reverse", 1),
                new ShuffleType("Almost", 2)
        );

        speeds = Arrays.asList(
                new Speed("Low", 200),
                new Speed("Medium", 50),
                new Speed("High", 7),
                new Speed("Very High", 1),
                new Speed("Extreme", 0)
        );
        resolutions = Arrays.asList(
                new Resolution("Low", 16),
                new Resolution("Medium", 64),
                new Resolution("High", 128),
                new Resolution("Very High", 256),
                new Resolution("Extreme", 512)
        );
        increments = Arrays.asList(
                new Increment("Slope", 0),
                new Increment("Stairs", 1)
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
        data.orderSlope(resolutions.get(0).getValue());
    }

    private void loadView() {
        window = new Gui(mixes, speeds, resolutions, increments, theme);
        window.setShuffleListener(e -> {
            if(!Algorithm.isBusy()) {
                window.resetTimer();
                if(window.getShuffleType() == 0) {
                    data.shuffleOrder();
                } else if (window.getShuffleType() == 1){
                    order();
                    data.reverseOrder();
                } else if (window.getShuffleType() == 2) {
                    order();
                    data.missplaceOne();
                }
                window.updateBars(data.getValues());
            }
        });
        window.updateBars(data.getValues());
        window.setResolutionListener(e -> {
            if(!Algorithm.isBusy()) {
                order();
                newGame();
            }
        });
        window.setIncrementListener(e -> {
            if(!Algorithm.isBusy()) {
                order();
                newGame();
            }
        });
    }

    private void order() {
        if(window.getIncrements() == 0) {
            data.orderSlope(window.getResolution());
        } else if (window.getIncrements() == 1) {
            data.orderStairs(window.getResolution());
        }
    }

    private void newGame() {
        window.resetTimer();
        window.updateBars(data.getValues());
    }

    private void loadAlgorithms() {
        Algorithm.setData(data);
        window.setAlgorithms(new Controller(window, data).loadAlgorithms());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

}