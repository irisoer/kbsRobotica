package GUI;

import javax.swing.*;
import java.awt.*;
//Sylvia >
public interface Lettertype {                //Standaard instellingen alle schermen  todo: rename font naar lettertype (lt)
    Font fontHeading = new Font("Calibri",Font.BOLD, 42);
    Font fontTekst = new Font ("Calibri", Font.PLAIN, 28);
    Font fontSubTekst = new Font ("Calibri", Font.BOLD, 18);
    JLabel jlHeading = new JLabel();        //Heading tekst
    JLabel jlTekst = new JLabel();          //Normale tekst
    JLabel jlSubTekst = new JLabel();       //Kleine tekst (kleur labels bij sorteren)
}
//Sylvia <