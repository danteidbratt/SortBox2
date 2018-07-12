package view.Swing;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;

final class LogoPanel extends AbstractPanel {

    LogoPanel(TimePanel timePanel) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 100));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        setBackground(theme.getBackgroundColor());
        JLabel logo = new JLabel("SortBox");
        logo.setFont(new Font("SansSerif", Font.PLAIN, 50));
        logo.setForeground(theme.getLogoColor());
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        add(logo, WEST);
        add(timePanel, EAST);
    }

}