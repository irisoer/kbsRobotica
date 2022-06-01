package GUI;
import javax.swing.*;
import java.awt.*;

//Sylvia
public class ErrorScherm extends Scherm {
    private JLabel jlError;
    private JLabel jlErrorMessage;
    private JLabel jlTeruggaan;
    private JButton jbTerugNaarBeginScherm;

    public ErrorScherm() {
//      super("Error");
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout (new GridLayout(3,1));

        //componenten aanmaken
        jlError = new JLabel("Er is een fout opgetreden!");
        jlError.setFont(fontHeading);
        this.jlError.setHorizontalAlignment(SwingConstants.CENTER);
        jlErrorMessage = new JLabel("Controleer of alles goed is aangesloten");
        jlErrorMessage.setFont(fontTekst);
        this.jlErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        jlTeruggaan = new JLabel("Klik om terug te gaan");
        jlTeruggaan.setFont(fontSubTekst);
        this.jlTeruggaan.setHorizontalAlignment(SwingConstants.CENTER);

        //toevoegen
        panel.add(jlError);
        panel.add(jlErrorMessage);
        panel.add(jlTeruggaan);
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
