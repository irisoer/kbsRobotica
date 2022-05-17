package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class TestScherm extends JFrame implements ActionListener {
	JPanel testPanel;
	JButton jButtonL = new JButton("<");
	JButton jButtonR = new JButton(">");

	public TestScherm(String titel) {
		setTitle(titel);
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEADING));

		jButtonL.addActionListener(this);
		jButtonL.setPreferredSize(new Dimension(50, 400));
		add(jButtonL);

		testPanel = new JPanel(new CardLayout());
		testPanel.setSize(new Dimension(700, 400));

		testPanel.add(new VerwerkKleurPanel(), "VKleurP");
		testPanel.add(new VerwerkenKleur(), "VKleur");
		testPanel.add(new VerwerkenDoos(), "VDoos");

		add(testPanel);
		jButtonR.addActionListener(this);
		add(jButtonR);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(testPanel.getLayout());
		cl.next(testPanel);
	}
}
