package Applicatie;

import GUI.SorteerScherm;
import jssc.*;

import static jssc.SerialPort.*;

public abstract class Arduino {
	static String used = "";
	static char data = ' ';
	protected SerialPort serialPort;


	public Arduino(char readyChar) {
		SerialPort poort;
		for (String port : SerialPortList.getPortNames()) {
			if (serialPort != null || port.equals(used)) continue;
			try {
				poort = new SerialPort(port);
				poort.openPort();
				System.out.println("Test " + port);
				poort.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
				char ch = (char) poort.readBytes(1)[0];
				while (ch != '!') {
					System.out.print(ch);
					ch = (char) poort.readBytes(1)[0];
				}
				;
				char lh = (char) poort.readBytes(1)[0];
				System.out.println("ja " + ch + lh);
				if (lh == readyChar) {
					System.out.println(port + getClass());
					this.serialPort = poort;
					poort.addEventListener(new MyPortListener(this.serialPort));
					used = port;
				}
				;
				poort.closePort();
			} catch (SerialPortException e) {
				continue;
			}

		}
	}

	public static void sorteer(ArduinoInpak inpak, ArduinoSorteer sorteer, SorteerScherm sorteerScherm) throws SerialPortException, InterruptedException {
        sorteer.openPort(new ArduinoSorteer.SorteerListener(sorteer.serialPort, sorteerScherm));

//		while (true) {
////			sorteer.bandAan();
////			int index = -1;
////			String test = "#";
//////            while (sorteer.serialPort.readBytes(1)[0] != test.getBytes()[0]) {
//////            }
//////            char color = (char) sorteer.serialPort.readBytes(1)[0];
////			char color = data;
////			String kleur = "";
////			System.out.println(color);
////			if (color == 'r') index = 0;
////			else if (color == 'g') index = 1;
////			else if (color == 'b') index = 2;
////			else if (color == 's') break;
////			sorteer.closePort();
////			inpak.draaiNaarPlatform(index);
////			sorteer.openPort();
////			sorteer.serialPort.writeString("1:");
//		}
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	public void openPort(SerialPortEventListener eventListener) throws SerialPortException {
		this.serialPort.openPort();

		this.serialPort.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
//		char ch = (char) this.serialPort.readBytes(1)[0];
//		while (ch != '!') {
//			System.out.print(ch);
//			ch = (char) this.serialPort.readBytes(1)[0];
//		};
//		char lh = (char) this.serialPort.readBytes(1)[0];
		int mask = MASK_RXCHAR;
		this.serialPort.setEventsMask(mask);
		this.serialPort.addEventListener(eventListener);
	}

	public void closePort() throws SerialPortException {
		this.serialPort.closePort();
	}
	// Beide arduino's moeten kleuren kunnen scannen
	// Beide arduino's moeten connectie kunnen maken

	public class MyPortListener implements SerialPortEventListener {
		String buffer = "";

        SerialPort port;

        public MyPortListener(SerialPort port) {
            this.port = port;
        }

		private void onMessage() {
			// constructing message
			System.out.println("RECEIVED MESSAGE: " + buffer);
			buffer = "";
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					String b = this.port.readString(event.getEventType());
					System.out.println("event:" + b);
					if (b.equals("\n")) {
						onMessage();
					} else {
						buffer += b;
					}
				} catch (SerialPortException ex) {
					System.out.println("Error in receiving string from COM-port: " + ex);
				}
			}
		}

	}
}
