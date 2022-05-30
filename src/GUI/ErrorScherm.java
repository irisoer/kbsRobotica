package GUI;
import javax.swing.*;
import java.awt.*;

//Sylvia
public class ErrorScherm extends Scherm {
    private JLabel jlError;
    private JLabel jlErrorMessage;
    private String bericht = "ERROR MESSAGE";  //todo: specifiek bericht meegeven

    public ErrorScherm() {
//        super("Error");
        setLayout (new GridLayout(2,1));

        //componenten aanmaken
        jlHeading.setText("Er is een fout opgetreden!");
        jlTekst.setText(bericht);

        //toevoegen
        add(jlHeading);
        add(jlTekst);
        setVisible(true);
    }

}
