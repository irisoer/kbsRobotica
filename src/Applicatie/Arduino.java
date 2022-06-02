package Applicatie;

import GUI.EindschermOrderKlaarmaken;
import GUI.Frame;
import GUI.SorteerScherm;
import GUI.VerwerkScherm;
import jssc.*;

import static jssc.SerialPort.*;

public abstract class Arduino {
	static String used = "";            // Vult de gebruikte port in
	static char data = ' ';                // Laaste payload die ontvangen is
	protected SerialPort serialPort;    // COM port die verbonden is


	public Arduino(char readyChar) {
		SerialPort poort;
		// Zoeken naar port check alle porten en slaan porten die gebruikt zijn over
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
				char lh = (char) poort.readBytes(1)[0];
				System.out.println("ja " + ch + lh);
				if (lh == readyChar) {
					System.out.println(port + getClass());
					this.serialPort = poort;
					poort.addEventListener(new MyPortListener());
					used = port;
				}

				poort.closePort();
			} catch (SerialPortException e) {
				continue;
			}

		}
	}

	public static void openPort(SerialPort port, SerialPortEventListener eventListener) throws SerialPortException {
		port.openPort();

		port.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
		int mask = MASK_RXCHAR;
		port.setEventsMask(mask);
		port.addEventListener(eventListener);
	}

	public static void openPorts(Arduino ard1, Arduino ard2) {
		ard1.openPort();
		ard2.openPort();
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	public void openPort() {
		if (this.serialPort.isOpened()) return;
		try {
			this.serialPort.openPort();
			this.serialPort.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);
			this.serialPort.setEventsMask(MASK_RXCHAR);
			this.serialPort.addEventListener(new MyPortListener(), MASK_RXCHAR);
		} catch (SerialPortException e) {
			throw new RuntimeException(e);
		}
	}

	public void closePort() {
		try {
			this.serialPort.closePort();
		} catch (SerialPortException e) {
		}
	}
	// Beide arduino's moeten kleuren kunnen scannen
	// Beide arduino's moeten connectie kunnen maken

	public class MyPortListener implements SerialPortEventListener {
		public static Staat huidigeStaat;
		public static Taak huidigeTaak;
		String buffer = "";
		private final SerialPort port = getSerialPort();
		private final String name = Arduino.this.getClass().getSimpleName();

		public int getIndexFromColor(char color) {
			switch (huidigeTaak) {
				case Sorteer -> {
					return switch (color) {
						case 'r' -> 0;
						case 'g' -> 1;
						case 'b' -> 2;
						default -> -1;
					};
				}
				case Inpak -> {
					String kleur = switch (color) {
						case 'r' -> "Red";
						case 'g' -> "Yellow";
						case 'b' -> "Blue";
						default -> "";
					};
					Artikel a = Database.selecteerArtikel(kleur);
					System.out.println(a);
					int index = Order.getBpp().findBinNum(a);
					return index;
				}
			}
			return -1;
		}

		private void verstuurKleur(char color) {
			MyPortListener.huidigeStaat = Staat.DraaiNaarPlatform;
			try {
				int index = getIndexFromColor(color);
				System.out.println(index);
				if (index != -1) Frame.arduinoInpak.serialPort.writeString(index + ":");
				else {
					Frame.arduinoSorteer.bandAan();
					huidigeStaat = Staat.WachtOpScan;
				}
				Frame.huidigeDoos = index;
				if (index != -1) switch (color) {
					case 'r' -> Frame.aantalRood++;
					case 'g' -> Frame.aantalGeel++;
					case 'b' -> Frame.aantalBlauw++;
					default -> {
					}
				}
			} catch (SerialPortException e) {
			}
			Frame.huidigeKleur = color;
			System.out.println("test" + color);
			if (huidigeTaak.equals(Taak.Sorteer)) SorteerScherm.moduleData(color);
			else if (huidigeTaak.equals(Taak.Inpak)) {
				for (VerwerkScherm.Carrousel carrousel : VerwerkScherm.Carrousel.values()) {
					carrousel.scherm.reload();
				}
				if (Order.getBpp().isLeeg()) {

					Frame.setScherm(Frame.Schermen.EindSchermOrderKlaarmaken);
					EindschermOrderKlaarmaken.runEindProces();
				}
			}
		}

		private void klaarMetDraaien() {
			huidigeStaat = Staat.PusherAan;
			try {
				Frame.arduinoSorteer.serialPort.writeString("1:");
			} catch (SerialPortException e) {
			}
			huidigeStaat = Staat.WachtOpScan;
		}

		private void onMessage() {
			// constructing message

			byte[] bufferBytes = buffer.getBytes();
			switch (name) {
				case "ArduinoInpak" -> {
					if (huidigeStaat == null) break;
					System.out.println(huidigeStaat);
					System.out.println(huidigeTaak.toString());
					switch (huidigeStaat) {
						case DraaiNaarPlatform -> {
							for (byte bufferByte : bufferBytes) {
								System.out.println((char) bufferByte + "test");
								if (bufferByte == ':') {
									klaarMetDraaien();
									return;
								}
							}
						}
						default -> {
						}
					}
				}
				case "ArduinoSorteer" -> {
					if (huidigeStaat == null) break;
					switch (huidigeStaat) {
						case WachtOpScan -> {
							for (int i = 0; i < bufferBytes.length; i++) {
								if (bufferBytes[i] == '#' && bufferBytes[i + 1] != ' ') {
									verstuurKleur((char) bufferBytes[i + 1]);
								}
							}
						}
						default -> {
						}
					}
				}
				default -> System.out.println(name);
			}
			buffer = "";
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					String b = this.port.readString(event.getEventType());
					System.out.println("Data: " + b);
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

		public enum Taak {
			Sorteer, Inpak, Geen
		}

		public enum Staat {
			ZetBandAan, WachtOpScan, DraaiNaarPlatform, PusherAan
		}

	}
}
