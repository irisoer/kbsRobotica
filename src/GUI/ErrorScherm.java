package GUI;
import javax.swing.*;
import java.awt.*;

//Sylvia
public class ErrorScherm extends Scherm {
    private JLabel jlError;
    private JLabel jlErrorMessage;
    private JButton jbTerugNaarBeginScherm;
    private String bericht = "Controleer of alles goed is aangesloten";

    public ErrorScherm() {
//        super("Error");
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout (new GridLayout(2,1));

        //componenten aanmaken
        jlHeading.setText("Er is een fout opgetreden!");
        jlTekst.setText(bericht);

        //toevoegen
        panel.add(jlHeading);
        panel.add(jlTekst);
        panel.setBounds(0,0,800,480);
        add(panel);
        jbTerugNaarBeginScherm = new JButton();
        jbTerugNaarBeginScherm.addActionListener(e -> Frame.setScherm(Frame.Schermen.OpstartScherm));
        jbTerugNaarBeginScherm.setBounds(0,0,800,480);
        jbTerugNaarBeginScherm.setOpaque(false);
        jbTerugNaarBeginScherm.setBorderPainted(false);
        jbTerugNaarBeginScherm.setFocusPainted(false);
        jbTerugNaarBeginScherm.setContentAreaFilled(false);
        add(jbTerugNaarBeginScherm);
        setVisible(true);
    }

}
