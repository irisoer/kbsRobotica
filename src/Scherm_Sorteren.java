import javax.swing.*;
import java.awt.*;

public class Scherm_Sorteren extends JFrame{
    private JLabel jlTitel;
    private PanelSorteren panel;
    private JLabel jlGroenAantal;
    private JLabel jlBlauwAantal;
    private JLabel jlRoodAantal;

    public Scherm_Sorteren() {
        setTitle("Sorteren");
        setSize(800, 480);
        setLayout (new GridLayout(2,0));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //componenten aanmaken
        jlTitel = new JLabel("Uw voorraad wordt gesorteerd");
        panel = new PanelSorteren();
        jlGroenAantal = new JLabel("x");
        jlBlauwAantal = new JLabel("x");
        jlRoodAantal = new JLabel("x");

        //toevoegen
        add(jlTitel);
       // jlTitel.setHorizontalAlignment(JLabel.CENTER);
        add(panel);
        add(jlGroenAantal);
        add(jlBlauwAantal);
        add(jlRoodAantal);
        //setComponentOrientation(ComponentOrientation.);

        setVisible(true);
    }

}
