package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements Layout{

        public Frame(){
                setTitle("");
                setSize(800, 400);
                setLayout(new CardLayout());
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }


}
