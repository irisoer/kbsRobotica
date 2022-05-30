package GUI;

import Applicatie.*;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;

public class OpstartScherm extends Scherm{
    private JLabel jlArduinoSorteer;
    private JLabel jlArduinoInpak;
    private JLabel jlIsVerbondenInpak;
    private JLabel jlIsVerbondenSorteer;

    public OpstartScherm() throws SerialPortException, InterruptedException {
        this.setSize(800,480);
        this.setLayout(new GridLayout(4,1));

        ArduinoInpak inpak = new ArduinoInpak();
        inpak.draaiNaarPlatform(0);




        jlArduinoSorteer = new JLabel("Applicatie.Arduino sorteren connecten: ");
        jlArduinoSorteer.setFont(fontTekst);

        jlIsVerbondenSorteer = new JLabel();

        add(jlArduinoSorteer,jlIsVerbondenSorteer);



        jlArduinoInpak = new JLabel("Applicatie.Arduino inpakken connecten: ");
        jlArduinoInpak.setFont(fontTekst);
        add(jlArduinoInpak);



        this.setVisible(true);
    }

}
