package view.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;

final class MenuPanel extends AbstractPanel {

    private final JButton shuffleButton;
    private final JButton exitButton;

    MenuPanel() {
        setLayout(new GridLayout(2,1));
        setBackground(theme.getBackgroundColor());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        shuffleButton = new JButton("Shuffle");
        shuffleButton.setFont(new Font("SansSerif", BOLD, 25));

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setFont(new Font("SansSerif", BOLD, 25));

        add(shuffleButton);
        add(exitButton);
    }

    public void setShuffleListener(ActionListener shuffleListener) {
        shuffleButton.addActionListener(shuffleListener);
    }
}
