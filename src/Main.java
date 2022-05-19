public class Main {
	public static void main(String[] args) throws InterruptedException {
		GUI gui = new GUI();

		while(true) {
			while(!gui.startScherm.volgende) {
				Thread.sleep(50);
			}
			gui.setScherm(GUI.Schermen.CAROUSSEL);
		}


	}
}
