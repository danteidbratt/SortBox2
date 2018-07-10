package controller.Sorting;

import model.Retreivable;

import java.awt.event.ActionListener;

public final class Algorithm {

    private static Retreivable data;
    private static boolean busy;
    private final String identifier;
    private final Runnable algorithm;
    private final ActionListener actionListener;


    Algorithm(String identifier, Runnable algorithm) {
        this.identifier = identifier;
        this.algorithm = algorithm;
        actionListener = e -> activate();
    }

    private void activate() {
        if(data.isShuffled() && !busy) {
            busy = true;
            new Thread(algorithm).start();
        }
    }

    public static void setData(Retreivable data) {
        Algorithm.data = data;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    static void setBusy(boolean busy) {
        Algorithm.busy = busy;
    }

    public static boolean isBusy() {
        return busy;
    }

}