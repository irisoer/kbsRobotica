package GUI;

import javax.swing.*;

//Sylvia >
public class Scherm extends JFrame implements Lettertype {                                    //naar interface lettertype
//    protected Font fontHeading = new Font("Calibri",Font.BOLD, 42);
//    protected Font fontTekst = new Font ("Calibri", Font.PLAIN, 28);
//    protected Font fontSubTekst = new Font ("Calibri", Font.PLAIN, 18);
//    JLabel jlHeading = new JLabel();        //Heading tekst
//    JLabel jlTekst = new JLabel();          //Normale tekst
//    JLabel jlSubTekst = new JLabel();       //Kleine tekst (kleur labels bij sorteren)

    public Scherm(String titel){                                        //Standaard opmaak, titel = titel in balk
        setTitle(titel);
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jlHeading.setFont(fontHeading);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);
        jlTekst.setFont(fontTekst);
        jlTekst.setHorizontalAlignment(JLabel.CENTER);
        jlSubTekst.setFont(fontSubTekst);
        jlSubTekst.setHorizontalAlignment(JLabel.CENTER);

//        setVisible(true);
    }

    //todo: icon veranderen

}
//Sylvia <
