package GUI;

import java.awt.*;

public class VerwerkCarrouselScherm extends Scherm {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//pijl links
		Polygon pijlL = new Polygon();
		pijlL.addPoint(80,180);     //bovenste punt
		pijlL.addPoint(80,200);     //onderste punt
		pijlL.addPoint( 65,190);   //zij punt
		g.fillPolygon(pijlL);
		//pijl rechts
		Polygon pijlR = new Polygon();
		pijlR.addPoint(720,180);     //bovenste punt
		pijlR.addPoint(720,200);     //onderste punt
		pijlR.addPoint( 735,190);    //zij punt
		g.fillPolygon(pijlR);

	}


	public void reload() {

	}
}
