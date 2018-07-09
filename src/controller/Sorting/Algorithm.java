package controller.Sorting;

import java.awt.event.ActionListener;

public class Algorithm {

    private final String identifier;
    private final ActionListener actionListener;

    public Algorithm(String identifier, Runnable algorithm) {
        this.identifier = identifier;
        actionListener = e -> new Thread(algorithm).start();
    }

    public String getIdentifier() {
        return identifier;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

}