import javax.swing.*;
import java.awt.*;

//Sylvia
public class SchermError extends JFrame {

    private JLabel jlError;
    private JLabel jlErrorMessage;


    public SchermError() {
        setTitle("Error");
        setSize(300, 200);
        setLayout (new GridLayout(2,1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //componenten aanmaken
        jlError = new JLabel("Er is een fout opgetreden!");
        jlErrorMessage = new JLabel( "ERROR MESSAGE");

        //toevoegen
        add(jlError);
        jlError.setHorizontalAlignment(JLabel.CENTER);
        add(jlErrorMessage);
        jlErrorMessage.setHorizontalAlignment(JLabel.CENTER);
        setVisible(true);
    }


}
