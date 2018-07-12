package view.Swing;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;

import static javax.swing.SwingConstants.RIGHT;

class TimePanel extends AbstractPanel {

    private final Timer timer;
    private Duration duration;
    private final JLabel timeLabel;

    TimePanel() {
        timer = new Timer(100, e -> tick());
        timeLabel = new JLabel();
        duration = Duration.ZERO;
        setTime(duration);
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        timeLabel.setForeground(theme.getLogoColor());
        timeLabel.setHorizontalAlignment(RIGHT);
        setBackground(theme.getBackgroundColor());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(timeLabel);
    }

    private void tick() {
        duration = duration.plusMillis(100);
        setTime(duration);
    }

    private void setTime(Duration duration) {
        timeLabel.setText(String.format("%.1f", (duration.toMillis() * 1.0) / 1000));
    }

    void start() {
        new Thread(timer::start).start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        duration = Duration.ZERO;
        setTime(duration);
    }
}
