package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

//Sylvia >  todo: GREEN > ORANGE
public class Scherm extends JPanel implements Layout { //wordt panel
//    private VerwerkScherm verwerk = new VerwerkScherm();
    public Scherm(){                       //Standaard opmaak, titel = titel in balk
        // nieuwe klasse > extends JFrame

        JFrame frame = new Frame();
//        JButton jbPijlLinks = new BasicArrowButton(BasicArrowButton.WEST);
//        JButton jbPijlRechts = new BasicArrowButton(BasicArrowButton.EAST);
//        add(jbPijlLinks);
////        jbPijlLinks.setHorizontalAlignment(JButton.WEST);
//        add(jbPijlRechts);
////        jbPijlRechts.setHorizontalAlignment(JButton.EAST);

        jlHeading.setFont(fontHeading);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);
        jlTekst.setFont(fontTekst);
        jlTekst.setHorizontalAlignment(JLabel.CENTER);
//        jlSubTekst.setFont(fontSubTekst);
//        jlSubTekst.setHorizontalAlignment(JLabel.CENTER);

//        setVisible(true);
    }

    //todo: icon veranderen

}
//Sylvia <
