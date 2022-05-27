package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

//Sylvia >
public class VerwerkScherm extends Scherm{

    JButton jbPijlLinks = new BasicArrowButton(BasicArrowButton.WEST);
    JButton jbPijlRechts = new BasicArrowButton(BasicArrowButton.EAST);

    public VerwerkScherm() {
//        super("Verwerken order");
        setLayout(new FlowLayout());
        jlHeading.setText("Uw order wordt verwerkt:");
//        add(jlHeading);
//        add(jbPijlLinks);
//        add(jbPijlRechts); //todo: pijltjes
//
    }



}
//Sylvia <