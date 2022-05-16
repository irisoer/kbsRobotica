package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia
public class Scherm extends JFrame {                                    //Standaard instellingen alle schermen
    protected Font fontHeading = new Font("Calibri",Font.BOLD, 42);
    protected Font fontTekst = new Font ("Calibri", Font.PLAIN, 28);
    protected Font fontSubTekst = new Font ("Calibri", Font.PLAIN, 18);
    JLabel jlHeading = new JLabel();
    JLabel jlTekst = new JLabel();
    JLabel jlSubTekst = new JLabel();

    public Scherm(String titel){                                        //Standaard opmaak
        setTitle(titel);
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jlHeading.setFont(fontHeading);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);
        jlTekst.setFont(fontTekst);
        jlTekst.setHorizontalAlignment(JLabel.CENTER);
        jlSubTekst.setFont(fontSubTekst);
        jlSubTekst.setHorizontalAlignment(JLabel.CENTER);

        setVisible(true);
    }

    //todo: icon veranderen


}
