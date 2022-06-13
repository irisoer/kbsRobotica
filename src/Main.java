import Applicatie.*;
import GUI.Frame;
import jssc.SerialPortException;


public class Main {

	public static void main(String[] args) throws SerialPortException, InterruptedException {;

		Frame frame = new Frame();
		Arduino.openPorts(Frame.arduinoInpak, Frame.arduinoSorteer);

	}
}

