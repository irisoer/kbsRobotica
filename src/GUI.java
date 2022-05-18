import JPanels.CarousselScherm;
import JPanels.Panel;
import JPanels.StartScherm;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	JPanel schermen = new JPanel(new CardLayout());
	StartScherm startScherm = new StartScherm(new int[]{5, 7, 4});
	CardLayout cards = (CardLayout)(schermen.getLayout());
	JPanel huidigScherm;


	public GUI() {
		super("Gui");
		setPreferredSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		schermen.add(startScherm, 0);
		schermen.add(new CarousselScherm(), 1);
		schermen.add(new Panel(), 2);

		add(schermen);
		setResizable(false);
		setVisible(true);
	}

	public void volgendeScherm() {
		cards.next(schermen);
	}
}
