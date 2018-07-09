package unit;

import java.awt.*;

public class Theme {

    private final Color activeColor;
    private final Color inactiveColor;
    private final Color backgroundColor;
    private final Color scanningColor;
    private final Color logoColor;

    public Theme(Color activeColor, Color inactiveColor, Color backgroundColor, Color scanningColor, Color logoColor) {
        this.activeColor = activeColor;
        this.inactiveColor = inactiveColor;
        this.backgroundColor = backgroundColor;
        this.scanningColor = scanningColor;
        this.logoColor = logoColor;
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

    public Color getLogoColor() {
        return logoColor;
    }
}