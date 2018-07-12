package view.Swing;

import unit.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.BorderLayout.*;

class Dashboard extends AbstractPanel {

    private final JButton shuffleButton;
    private final List<Setting> shuffleTypes;
    private final List<Setting> resolutions;
    private final List<Setting> speeds;
    private final List<Setting> increments;
    private final JComboBox<String> shuffleTypeBox;
    private final JComboBox<String> resolutionBox;
    private final JComboBox<String> speedBox;
    private final JComboBox<String> incrementBox;

    Dashboard(List<Setting> shuffleTypes, List<Setting> resolutions, List<Setting> speeds, List<Setting> increments, ActionListener speedListener) {
        this.shuffleTypes = shuffleTypes;
        this.resolutions = resolutions;
        this.speeds = speeds;
        this.increments = increments;
        shuffleTypeBox = new JComboBox<>(extractKeys(shuffleTypes));
        resolutionBox = new JComboBox<>(extractKeys(resolutions));
        speedBox = new JComboBox<>(extractKeys(speeds));
        speedBox.addActionListener(speedListener);
        incrementBox = new JComboBox<>(extractKeys(increments));
        setLayout(new BorderLayout());

        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(2, 4, 20, 20));
        boxPanel.setPreferredSize(new Dimension(0, 130));
        boxPanel.setBackground(theme.getBackgroundColor());
        boxPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));

        JLabel shuffleTypeLabel = new JLabel("Shuffle Type");
        JLabel resolutionLabel = new JLabel("Resolution");
        JLabel speedLabel = new JLabel("Speed");
        JLabel incrementLabel = new JLabel("Increment");

        for (JLabel label : Arrays.asList(shuffleTypeLabel, resolutionLabel, speedLabel, incrementLabel)) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 20));
            label.setForeground(theme.getLogoColor());
            label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            boxPanel.add(label);
        }

        for (JComboBox<String> box : Arrays.asList(shuffleTypeBox, resolutionBox, speedBox, incrementBox)) {
            box.setFont(new Font("SansSerif", Font.BOLD, 20));
            ((JLabel) box.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            boxPanel.add(box);
        }

        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();

        for (JPanel panel : Arrays.asList(westPanel, eastPanel)) {
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(250, 0));
            panel.setBackground(theme.getBackgroundColor());
        }

        westPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

        shuffleButton = new JButton("Shuffle");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        for (JButton button : Arrays.asList(shuffleButton, exitButton)) {
            button.setFont(theme.getButtonFont());
        }

        westPanel.add(shuffleButton, CENTER);
        eastPanel.add(exitButton, CENTER);

        add(boxPanel, CENTER);
        add(westPanel, WEST);
        add(eastPanel, EAST);
    }

    private String[] extractKeys(List<Setting> settings) {
        return settings.stream()
                .map(Setting::getIdentifier)
                .collect(Collectors.toList())
                .toArray(new String[settings.size()]);
    }

    int getShuffleType() {
        return getSetting(shuffleTypes, shuffleTypeBox);
    }

    int getResolution() {
        return getSetting(resolutions, resolutionBox);
    }

    int getSpeed() {
        return getSetting(speeds, speedBox);
    }

    int getIncrements() {
        return getSetting(increments, incrementBox);
    }

    private int getSetting(List<Setting> settings, JComboBox<String> comboBox) {
        for (Setting setting : settings) {
            if (setting.getIdentifier().equals(comboBox.getSelectedItem())) {
                return setting.getValue();
            }
        }
        return -1;
    }

    void setResolutionListener(ActionListener resolutionListener) {
        resolutionBox.addActionListener(resolutionListener);
    }

    void setShuffleListener(ActionListener shuffleListener) {
        shuffleButton.addActionListener(shuffleListener);
    }

    void setIncrementListener(ActionListener incrementListener) {
        incrementBox.addActionListener(incrementListener);
    }
}
