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
//
//		VerwerkKleurScherm vk = new VerwerkKleurScherm();		//todo: meer aantallen (net als in dozen)
//		frame.add(vk);
//
		VerwerkDoosScherm vd = new VerwerkDoosScherm();			//todo: nog niet volledig werkend
		frame.add(vd);

////
//		VerwerkVoorraadScherm vv = new VerwerkVoorraadScherm();
//		frame.add(vv);

		frame.setVisible(true);

	}
}
