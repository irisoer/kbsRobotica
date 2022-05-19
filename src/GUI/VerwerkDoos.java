package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkDoos extends VerwerkScherm {
    private JPanel jpVerwerkDoos = new VerwerkDoosPanel();

    public VerwerkDoos() {
     super();
     setLayout(new FlowLayout());
     add(jpVerwerkDoos);

     setVisible(true);
    }

}
//Sylvia <