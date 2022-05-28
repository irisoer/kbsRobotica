package GUI;

import javax.swing.*;

//Sylvia >  todo: GREEN > ORANGE
public class Scherm extends JPanel implements Layout { //wordt panel
//    private VerwerkScherm verwerk = new VerwerkScherm();
    public Scherm(/*String titel*/){                       //Standaard opmaak, titel = titel in balk
        // nieuwe klasse > extends JFrame
//        setTitle(titel);
//        setSize(800, 400);
//        setLayout(new FlowLayout());
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new CardLayout());


//        add(verwerk);
//        add(new VerwerkDoosScherm());
//        add(new ErrorScherm()); //<nieuwe klasse
////        add(new Carrousel());

        jlHeading.setFont(fontHeading);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);
        jlTekst.setFont(fontTekst);
        jlTekst.setHorizontalAlignment(JLabel.CENTER);
//        jlSubTekst.setFont(fontSubTekst);
//        jlSubTekst.setHorizontalAlignment(JLabel.CENTER);

        setVisible(true);
    }

    //todo: icon veranderen

}
//Sylvia <
