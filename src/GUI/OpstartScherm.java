package GUI;

import Applicatie.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpstartScherm extends Scherm{
    private JLabel jlArduinoSorteer;        //aanmaken later te gebruiken labels
    private JLabel jlArduinoInpak;
    private JLabel jlDatabase;
    private JLabel jlIsVerbondenInpak;
    private JLabel jlIsVerbondenSorteer;
    private JLabel jlIsVerbondenDatabase;


    public static boolean verbonden = false;

    public OpstartScherm(){
        this.setSize(800,480);
        this.setLayout(new GridLayout(4,2));
        this.setBorder(new EmptyBorder(75,125,0,0));

        jlDatabase = new JLabel("Database status: ");
        jlDatabase.setFont(fontTekst);

        jlIsVerbondenDatabase = new JLabel("niet verbonden");
        jlIsVerbondenDatabase.setFont(fontTekst);
        jlIsVerbondenDatabase.setForeground(Color.RED);
        add(jlDatabase);
        add(jlIsVerbondenDatabase);

        jlArduinoSorteer = new JLabel("Sorteer Arduino status:  ");
        jlArduinoSorteer.setFont(fontTekst);

        jlIsVerbondenSorteer = new JLabel("niet verbonden");
        jlIsVerbondenSorteer.setFont(fontTekst);
        jlIsVerbondenSorteer.setForeground(Color.RED);
        add(jlArduinoSorteer);
        add(jlIsVerbondenSorteer);

        jlArduinoInpak = new JLabel("Inpak Arduino status: ");
        jlArduinoInpak.setFont(fontTekst);
        jlIsVerbondenInpak = new JLabel("niet verbonden");
        jlIsVerbondenInpak.setFont(fontTekst);
        jlIsVerbondenInpak.setForeground(Color.RED);
        add(jlArduinoInpak);
        add(jlIsVerbondenInpak);

    }

    public void statusInpakArduino(){       //geeft door of de inpakarduino is verbonden of niet
        try{
            ArduinoInpak inpak = new ArduinoInpak();
            while(inpak.getSerialPort() == null) {
                inpak = new ArduinoInpak();
            }
            Frame.arduinoInpak = inpak;
            System.out.println(inpak.getSerialPort());
            jlIsVerbondenInpak.setForeground(Color.BLACK);
            jlIsVerbondenInpak.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusSorteerArduino(){     //geeft door of de sorteerarduino is verbonden of niet
        try{
            ArduinoSorteer sorteer = new ArduinoSorteer();
            while(sorteer.getSerialPort() == null) {
                sorteer = new ArduinoSorteer();
            }
            Frame.arduinoSorteer = sorteer;
            jlIsVerbondenSorteer.setForeground(Color.BLACK);
            jlIsVerbondenSorteer.setText("verbonden");
        } catch (Exception e){
        }
    }

    public void statusDatabase(){       //geeft door of de database is verbonden of niet
        boolean verbonden = false;
        while(!verbonden){
        try{
            Database.startConnection();
            jlIsVerbondenDatabase.setForeground(Color.BLACK);
            jlIsVerbondenDatabase.setText("verbonden");
            verbonden = true;
        }catch (Exception e){
        }}
    }

    public void runStatussen() {    //functie die de statussen runt
        statusDatabase();
        statusSorteerArduino();
        statusInpakArduino();
        try {
            Thread.sleep(1500);     //deze sleep zorgt ervoor dat het scherm enkele seconden blijft staan voor deze door springt
        } catch (InterruptedException e) {

        }
        verbonden = true;
        Frame.setScherm(Frame.Schermen.StartScherm);
    }

}
