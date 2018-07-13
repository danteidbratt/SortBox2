package controller.Sorting;

import model.Sortable;

import java.awt.event.ActionListener;

public final class Algorithm {

    private static Sortable data;
    private static boolean available;
    private final String identifier;
    private final Runnable algorithm;
    private final ActionListener actionListener;


    Algorithm(String identifier, Runnable algorithm) {
        this.identifier = identifier;
        this.algorithm = algorithm;
        actionListener = e -> activate();
    }

    private void activate() {
        if(data.isShuffled() && available) {
            available = false;
            new Thread(algorithm).start();
        }
    }

    public static void setData(Sortable data) {
        Algorithm.data = data;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public static void setAvailable(boolean available) {
        Algorithm.available = available;
    }

    public static boolean isAvailable() {
        return available;
    }

}