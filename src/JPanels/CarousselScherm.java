package JPanels;

import JPanels.Caroussel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarousselScherm extends Panel implements ActionListener {
	JPanel carousselPanel = new JPanel(new CardLayout());
	JButton jButtonL = new JButton("<");
	JButton jButtonR = new JButton(">");


	public CarousselScherm() {
		setLayout (new FlowLayout());
		carousselPanel.add(new Een(), "Een");
		carousselPanel.add(new Twee(), "Twee");
		carousselPanel.add(new Drie(), "Drie");
		Dimension dimension = new Dimension(50, 600);
		jButtonL.addActionListener(this);
		jButtonL.setPreferredSize(dimension);
		add(jButtonL);
		add(carousselPanel);
		jButtonR.setPreferredSize(dimension);
		jButtonR.addActionListener(this);
		add(jButtonR);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cards = (CardLayout)(carousselPanel.getLayout());
		if(e.getSource().equals(jButtonL)) {
			cards.next(carousselPanel);
		} else {
			cards.previous(carousselPanel);
		}
	}
}
