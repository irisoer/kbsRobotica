package JPanels;

import JPanels.CarousselScherm;
import JPanels.Panel;
import JPanels.StartScherm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
	JPanel schermen = new JPanel(new CardLayout());
	public StartScherm startScherm = new StartScherm(new int[]{5, 7, 4}, this);
	CardLayout cards = (CardLayout)(schermen.getLayout());
	Schermen huidigScherm;

	@Override
	public void actionPerformed(ActionEvent e) {
		this.cards.next(schermen);
	}

	public enum Schermen {
		START,
		CAROUSSEL,
		EIND
	}
	public GUI() {
		super("Gui");
		setPreferredSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		schermen.add(startScherm, String.valueOf(Schermen.START));
		schermen.add(new CarousselScherm(), String.valueOf(Schermen.CAROUSSEL));
		schermen.add(new Panel(), String.valueOf(Schermen.EIND));


		setScherm(Schermen.START);
		add(schermen);
		setResizable(false);
		setVisible(true);
	}

	public void setScherm(Schermen scherm) {
		cards.show(schermen, String.valueOf(scherm));
		huidigScherm = scherm;
	}
}
