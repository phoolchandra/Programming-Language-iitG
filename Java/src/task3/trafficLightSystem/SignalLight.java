package task3.trafficLightSystem;

import javax.swing.*;
import java.awt.*;

// graphic interface for traffic light each signal created here
class SignalLight extends JPanel {

    Color colorOn;  // contain color of the given traffic light
    int boundary = 10;  // contain width of border
    boolean lightChange;    // contain status if light changed
    int trafficLightRadius = 40;    // contain radius of traffic light

    // traffic light color and status initialised
    SignalLight(Color color) {
        colorOn = color;
        lightChange = true;
    }

    // traffic light color updated in interface
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 100, 100);

        // change color as per if traffic light on or off
        if (lightChange) {
            g.setColor(colorOn);
        } else {
            g.setColor(colorOn.darker().darker().darker());
        }
        g.fillOval(boundary, boundary, 2 * trafficLightRadius, 2 * trafficLightRadius);
    }

    // traffic light dimensions are calculated for interface
    public Dimension getPreferredSize() {
        int size = (trafficLightRadius + boundary) * 2;
        return new Dimension(size, size);
    }

    // traffic light color change status updated
    public void turnOn(boolean a) {
        lightChange = a;
        repaint();
    }
}
