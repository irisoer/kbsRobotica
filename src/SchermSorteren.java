import javax.swing.*;
import java.awt.*;

//Sylvia

public class SchermSorteren extends JFrame{
    private JLabel jlTitel;
    private PanelSorteren panel;
    private JLabel jlGroenAantal;
    private JLabel jlBlauwAantal;
    private JLabel jlRoodAantal;
    private JPanel jpAantallen;

    public SchermSorteren() {
        setTitle("Sorteren");
        setSize(800, 480);
        setLayout (new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //componenten aanmaken      todo: variabelen voor aantallen
        jlTitel = new JLabel("Uw voorraad wordt gesorteerd");
        panel = new PanelSorteren();
        jlGroenAantal = new JLabel("Groen");
        jlBlauwAantal = new JLabel("Blauw");
        jlRoodAantal = new JLabel("Rood");
        jpAantallen = new JPanel();
        jpAantallen.setLayout(new GridLayout(0,3));


        //toevoegen
        add(jlTitel);
        jlTitel.setHorizontalAlignment(JLabel.CENTER);
        add(panel);

        //voorraad aantallen todo: goed uitlijnen
        jpAantallen.add(jlGroenAantal);
        jpAantallen.add(jlBlauwAantal);
        jpAantallen.add(jlRoodAantal);
        add(jpAantallen);

        //setComponentOrientation(ComponentOrientation.);

        setVisible(true);
    }

}
