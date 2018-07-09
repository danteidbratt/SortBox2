package unit;

import java.awt.*;

public class Theme {

    private final Color activeColor;
    private final Color inactiveColor;
    private final Color backgroundColor;
    private final Color scanningColor;

    public Theme(Color activeColor, Color inactiveColor, Color backgroundColor, Color scanningColor) {
        this.activeColor = activeColor;
        this.inactiveColor = inactiveColor;
        this.backgroundColor = backgroundColor;
        this.scanningColor = scanningColor;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public Color getInactiveColor() {
        return inactiveColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getScanningColor() {
        return scanningColor;
    }

}