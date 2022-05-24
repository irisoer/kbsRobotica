package GUI;

import javax.swing.*;       //todo: check fonts in andere klasses (kunnen weg)
import java.awt.*;
//Sylvia >                                   //Lettertypes in interface zodat aantal/kleur in sorteerpanel als String getekend kan worden
public interface Layout {
    // Standaard schermen
    Dimension panelFormaat = new Dimension(800,350);
    Font fontHeading = new Font("Calibri",Font.BOLD, 42);
    Font fontTekst = new Font ("Calibri", Font.PLAIN, 28);
    Font fontSubTekst = new Font ("Calibri", Font.BOLD, 18);
    JLabel jlHeading = new JLabel();        //Heading tekst
    JLabel jlTekst = new JLabel();          //Normale tekst
    JLabel jlSubTekst = new JLabel();       //Kleine tekst

}
//Sylvia <