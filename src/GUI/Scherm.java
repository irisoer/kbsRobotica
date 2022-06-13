package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class Scherm extends JPanel implements Layout {      //basis voor alle schermen
    JLabel jlHeading = new JLabel();        //Heading tekst

    public Scherm(){
        setName(getClass().getSimpleName());
        jlHeading.setFont(fontTitel);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);

    }

    public void reload() {

    };
}
//Sylvia <
