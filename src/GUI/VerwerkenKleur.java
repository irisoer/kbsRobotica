package GUI;

import java.awt.*;

//Sylvia

public class VerwerkenKleur extends VerwerkScherm{

    public VerwerkenKleur(){
        super();
        setLayout (new GridLayout(2,3));

        //aanmaken
        jlSubTekst.setText("Groen         Blauw          Rood");

        //toevoegen
        add(this.jlHeading);
        add(jlSubTekst);

        setVisible(true);
    }

}
