import javax.swing.*;
import java.awt.*;

//Sylvia

public class SchermVerwerken extends JFrame{
    private JLabel titel;
    private PanelVerwerken panelVerwerk;

    public SchermVerwerken(){
        setTitle("Verwerken");
        setSize(800, 480);
        setLayout (new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //aanmaken
        panelVerwerk = new PanelVerwerken();
        titel = new JLabel("Uw order wordt verwerkt");

        //toevoegen
        add(titel);
        add(panelVerwerk);

        setVisible(true);
    }

}
