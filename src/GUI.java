import JPanels.CarousselScherm;
import JPanels.Panel;
import JPanels.StartScherm;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	JPanel schermen = new JPanel(new CardLayout());

	public GUI() {
		super("Gui");
		setPreferredSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		schermen.add(new StartScherm(), "Start");
		schermen.add(new CarousselScherm(), "Carrousel");
		schermen.add(new Panel(), "Panel");


		add(schermen);
		setResizable(false);
		setVisible(true);
	}

	public void volgendeScherm() {
		CardLayout cards = (CardLayout)(schermen.getLayout());
		cards.next(schermen);
	}
}
