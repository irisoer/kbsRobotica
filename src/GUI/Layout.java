package GUI;

import javax.swing.*;       //todo: check fonts in andere klasses (kunnen weg)
import java.awt.*;
//Sylvia >                                   //Lettertypes in interface zodat aantal/kleur in sorteerpanel als String getekend kan worden
public interface Layout {
    // Standaard schermen
    Font fontTitel = new Font("Helvetiva",Font.BOLD, 42);
    Font fontTekst = new Font ("Helvetiva", Font.PLAIN, 28);
    Font fontSubTekst = new Font ("Helvetiva", Font.PLAIN, 20);
}
//Sylvia <