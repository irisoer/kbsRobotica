package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class VerwerkKleur extends VerwerkScherm{
    private JPanel jpVerwerkKleur = new VerwerkKleurPanel();

    public VerwerkKleur(){
        super();
        setLayout (new FlowLayout());
        //todo: knoppen toevoegen
        add(jpVerwerkKleur);
        setVisible(true);
    }

}
//Sylvia <