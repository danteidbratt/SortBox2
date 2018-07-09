package view.Swing;

import unit.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SettingsPanel extends AbstractPanel {

    private final List<Setting> resolutions;
    private final List<Setting> speeds;
    private final JComboBox<String> resolutionBox;
    private final JComboBox<String> speedBox;

    SettingsPanel(List<Setting> resolutions, List<Setting> speeds, ActionListener speedListener) {
        this.resolutions = resolutions;
        this.speeds = speeds;
        resolutionBox = new JComboBox<>(extractKeys(resolutions));
        speedBox = new JComboBox<>(extractKeys(speeds));
        speedBox.addActionListener(speedListener);
        setBackground(theme.getBackgroundColor());
        setPreferredSize(new Dimension(0, 150));
        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(0, 200, 20, 200));

        JLabel resolutionLabel = new JLabel("Resolution");
        JLabel speedLabel = new JLabel("Speed");

        for (JLabel label : Arrays.asList(resolutionLabel, speedLabel)) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 20));
            label.setForeground(theme.getLogoColor());
            label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        }

        resolutionBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        speedBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        ((JLabel) speedBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) resolutionBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        add(resolutionLabel);
        add(speedLabel);
        add(resolutionBox);
        add(speedBox);
    }

    private String[] extractKeys(List<Setting> settings) {
        return settings.stream()
                .map(Setting::getIdentifier)
                .collect(Collectors.toList())
                .toArray(new String[settings.size()]);
    }

    int getResolution() {
        return getSetting(resolutions, resolutionBox);
    }

    int getSpeed() {
        return getSetting(speeds, speedBox);
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
}
