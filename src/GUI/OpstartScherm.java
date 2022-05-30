package GUI;

import Applicatie.*;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpstartScherm extends Scherm{
    private JLabel jlArduinoSorteer;
    private JLabel jlArduinoInpak;
    private JLabel jlDatabase;
    private JLabel jlIsVerbondenInpak;
    private JLabel jlIsVerbondenSorteer;
    private JLabel jlIsVerbondenDatabase;

    private JButton jbRestart;

    public static boolean verbonden = false;

    public OpstartScherm(){
        this.setSize(800,480);
        this.setLayout(new GridLayout(3,2));

        jlDatabase = new JLabel("Database connecten: ");
        jlDatabase.setFont(fontTekst);

        jlIsVerbondenDatabase = new JLabel("niet verbonden");
        jlIsVerbondenDatabase.setFont(fontTekst);
        jlIsVerbondenDatabase.setForeground(Color.RED);
        add(jlDatabase);
        add(jlIsVerbondenDatabase);

        jlArduinoSorteer = new JLabel("Arduino sorteren connecten: ");
        jlArduinoSorteer.setFont(fontTekst);

        jlIsVerbondenSorteer = new JLabel("niet verbonden");
        jlIsVerbondenSorteer.setFont(fontTekst);
        jlIsVerbondenSorteer.setForeground(Color.RED);
        add(jlArduinoSorteer);
        add(jlIsVerbondenSorteer);

        jlArduinoInpak = new JLabel("Arduino inpakken connecten: ");
        jlArduinoInpak.setFont(fontTekst);
        jlIsVerbondenInpak = new JLabel("niet verbonden");
        jlIsVerbondenInpak.setFont(fontTekst);
        jlIsVerbondenInpak.setForeground(Color.RED);
        add(jlArduinoInpak);
        add(jlIsVerbondenInpak);
    }

    public void statusInpakArduino(){
        try{
            ArduinoInpak inpak = new ArduinoInpak();
            while(inpak.getSerialPort() == null) {
                inpak = new ArduinoInpak();
            }
            System.out.println(inpak.getSerialPort());
            jlIsVerbondenInpak.setForeground(Color.BLACK);
            jlIsVerbondenInpak.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusSorteerArduino(){
        try{
            ArduinoSorteer sorteer = new ArduinoSorteer();
            while(sorteer.getSerialPort() == null) {
                sorteer = new ArduinoSorteer();
            }
            jlIsVerbondenSorteer.setForeground(Color.BLACK);
            jlIsVerbondenSorteer.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusDatabase(){
        try{
            Database.startConnection();
            jlIsVerbondenDatabase.setForeground(Color.BLACK);
            jlIsVerbondenDatabase.setText("verbonden");
        }catch (Exception e){
        }
    }

    public void runStatussen(){
//        statusDatabase();
//        statusSorteerArduino();
//        statusInpakArduino();
        verbonden = true;
    }

}
