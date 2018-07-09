package view.Swing;

import unit.Theme;

import javax.swing.*;

public abstract class AbstractPanel extends JPanel {

    protected static Theme theme;

    public static void setTheme(Theme theme1) {
        theme = theme1;
    }
}
