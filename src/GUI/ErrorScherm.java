package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia
public class ErrorScherm extends Scherm{
    private JLabel jlError;
    private JLabel jlErrorMessage;

    public ErrorScherm() {
        super("Error");
        setLayout (new GridLayout(2,1));

        //componenten aanmaken
        jlError = new JLabel("Er is een fout opgetreden!");
        jlError.setFont(fontHeading);
        jlErrorMessage = new JLabel( "ERROR MESSAGE");
        jlErrorMessage.setFont(fontTekst);

        //toevoegen
        add(jlError);
        jlError.setHorizontalAlignment(JLabel.CENTER);
        add(jlErrorMessage);
        jlErrorMessage.setHorizontalAlignment(JLabel.CENTER);
        setVisible(true);
    }


}
