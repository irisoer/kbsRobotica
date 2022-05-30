package GUI;

import Applicatie.*;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;

public class OpstartScherm extends Scherm{
    private JLabel jlArduinoSorteer;
    private JLabel jlArduinoInpak;
    private JLabel jlDatabase;
    private JLabel jlIsVerbondenInpak;
    private JLabel jlIsVerbondenSorteer;
    private JLabel jlIsVerbondenDatabase;

    public OpstartScherm(){
        this.setSize(800,480);
        this.setLayout(new GridLayout(3,2));

        jlDatabase = new JLabel("Database connecten: ");
        jlDatabase.setFont(fontTekst);

        jlIsVerbondenDatabase = new JLabel("niet verbonden");
        jlIsVerbondenDatabase.setFont(fontTekst);
        add(jlDatabase);
        add(jlIsVerbondenDatabase);

        jlArduinoSorteer = new JLabel("Arduino sorteren connecten: ");
        jlArduinoSorteer.setFont(fontTekst);

        jlIsVerbondenSorteer = new JLabel("niet verbonden");
        jlIsVerbondenSorteer.setFont(fontTekst);
        add(jlArduinoSorteer);
        add(jlIsVerbondenSorteer);

        jlArduinoInpak = new JLabel("Arduino inpakken connecten: ");
        jlArduinoInpak.setFont(fontTekst);
        jlIsVerbondenInpak = new JLabel("niet verbonden");
        jlIsVerbondenInpak.setFont(fontTekst);
        add(jlArduinoInpak);
        add(jlIsVerbondenInpak);

    }

    public void statusInpakArduino(){
        try{
            ArduinoInpak inpak = new ArduinoInpak();
            while(inpak.getSerialPort() != null) {
                System.out.println("test");
            }
            jlIsVerbondenInpak.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusSorteerArduino(){
        try{
            ArduinoSorteer sorteer = new ArduinoSorteer();
            jlIsVerbondenSorteer.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusDatabase(){
        try{
            Database.startConnection();
            jlIsVerbondenDatabase.setText("verbonden");
        }catch (Exception e){
        }
    }

}
