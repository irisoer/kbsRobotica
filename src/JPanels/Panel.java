package JPanels;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
	protected Font fontHeading = new Font("Calibri",Font.BOLD, 42);
	protected Font fontTekst = new Font ("Calibri", Font.PLAIN, 28);
	protected Font fontSubTekst = new Font ("Calibri", Font.PLAIN, 18);
	JLabel jlHeading = new JLabel();
	JLabel jlTekst = new JLabel();
	JLabel jlSubTekst = new JLabel();
	public boolean volgende = false;

	public Panel() {
		jlHeading.setFont(fontHeading);
		jlHeading.setHorizontalAlignment(JLabel.CENTER);
		jlTekst.setFont(fontTekst);
		jlTekst.setHorizontalAlignment(JLabel.CENTER);
		jlSubTekst.setFont(fontSubTekst);
		jlSubTekst.setHorizontalAlignment(JLabel.CENTER);
	}
}
