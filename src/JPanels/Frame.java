package JPanels;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private JPanels.orderScherm orderScherm = new orderScherm();

    public Frame() {
        setTitle("test");
        setSize(800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new Button("test1"));
        panel.add(new Button("test2"));
        this.add(orderScherm);
        this.add(panel);




        setVisible(true);
    }

}
