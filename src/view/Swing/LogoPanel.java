package view.Swing;

import javax.swing.*;
import java.awt.*;

final class LogoPanel extends AbstractPanel {

    LogoPanel() {
        setLayout(new GridLayout(1, 1));
        setPreferredSize(new Dimension(0, 100));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(theme.getBackgroundColor());
        JLabel logo = new JLabel("SortBox");
        logo.setFont(new Font("SansSerif", Font.PLAIN, 50));
        logo.setForeground(theme.getLogoColor());
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        add(logo);
    }

}