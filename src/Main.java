import GUI.*;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
	public static void main(String[] args) {

		//testen schermen >
		JFrame frame = new JFrame();
		frame.setSize(800, 400);
		frame.setLayout(new GridLayout());

//		ErrorScherm e = new ErrorScherm();
//		frame.add(e);

//		SorteerScherm s = new SorteerScherm();
//		frame.add(s);

		VerwerkKleur vk = new VerwerkKleur();		//todo: meer aantallen (net als in dozen)
		frame.add(vk);

//		VerwerkDoos vd = new VerwerkDoos();			//todo: nog niet volledig werkend
//		frame.add(vd);

//		VerwerkVoorraad vv = new VerwerkVoorraad();
//		frame.add(vv);

		frame.setVisible(true);

	}
}
