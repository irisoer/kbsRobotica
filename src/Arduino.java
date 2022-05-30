import jssc.*;
import jssc.SerialPort;
import jssc.SerialPortList;

import static jssc.SerialPort.*;
import static jssc.SerialPort.PARITY_NONE;

public abstract class Arduino {
    protected SerialPort serialPort;
    static String used = "";

    public Arduino(char readyChar) {
        SerialPort test;
        for (String port: SerialPortList.getPortNames()) {
            if(port.equals(used)) continue;
            try {
                test = new SerialPort(port);
                test.openPort();
                System.out.println("Test " + port);
                test.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
                char ch = (char) test.readBytes(1)[0];
                while(ch != '!') {
                    System.out.print(ch);
                    ch = (char) test.readBytes(1)[0];
                };
                char lh = (char) test.readBytes(1)[0];
                System.out.println("ja " + ch + lh);
                if(lh == readyChar) {
                    System.out.println(port + getClass());
                    this.serialPort = test;
                    used = port;
                };

                test.closePort();
            } catch (SerialPortException e) {
                continue;
            }

        }
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void openPort() throws SerialPortException {
        this.serialPort.openPort();

        this.serialPort.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
        char ch = (char) this.serialPort.readBytes(1)[0];
        while(ch != '!') {
            System.out.print(ch);
            ch = (char) this.serialPort.readBytes(1)[0];
        };
        char lh = (char) this.serialPort.readBytes(1)[0];
     }

     public void closePort() throws SerialPortException {
        this.serialPort.closePort();
     }

     public static void sorteer(ArduinoInpak inpak, ArduinoSorteer sorteer) throws SerialPortException, InterruptedException {
        while(true) {
            sorteer.bandAan();
            int index = -1;
            String test = "#";
            while (sorteer.serialPort.readBytes(1)[0] != test.getBytes()[0]) {
            }
            char color = (char) sorteer.serialPort.readBytes(1)[0];
            String kleur = "";
            System.out.println(color);
            if (color == 'r') index = 0;
            else if (color == 'g') index = 1;
            else if (color == 'b') index = 2;
            else if (color == 's') break;
            sorteer.closePort();
            inpak.draaiNaarPlatform(index);
            sorteer.openPort();
            sorteer.serialPort.writeString("1:");
        }
    }
    // Beide arduino's moeten kleuren kunnen scannen
    // Beide arduino's moeten connectie kunnen maken
    

}
