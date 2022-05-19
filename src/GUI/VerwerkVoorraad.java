package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class VerwerkVoorraad extends VerwerkScherm{

    public VerwerkVoorraad(){
        setLayout(new FlowLayout());
        jlHeading.setText("Huidige voorraad:");             //labels in card zetten?
        JLabel jlGroen = new JLabel("Groene producten:");
        JLabel jlBlauw = new JLabel("BLauwe producten:");
        JLabel jlRood = new JLabel("Rode producten:");

        int groenAantal = 5;
        int blauwAantal = 8;
        int roodAantal = 3;

        JLabel jlGAantal = new JLabel(String.valueOf(groenAantal));
        JLabel jlBAantal = new JLabel(String.valueOf(blauwAantal));
        JLabel jlRAantal = new JLabel((String.valueOf(roodAantal)));

       add(jlGroen);
       add(jlGAantal);
       add(jlBlauw);
       add(jlBAantal);
       add(jlRood);
       add(jlRAantal);

       setVisible(true);
    }
}
//Sylvia <