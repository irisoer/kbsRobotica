package Applicatie;

import GUI.Frame;
import GUI.SorteerScherm;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.sql.SQLException;

public class ArduinoSorteer extends Arduino {


    public ArduinoSorteer() {
        super('s');

    }

    public int getKleur(Bpp bpp, ArduinoInpak inpak) throws SerialPortException, InterruptedException {
        this.bandAan();
        int index = -1;
        String test = "#";
        while(this.serialPort.readBytes(1)[0] != test.getBytes()[0]) {}
        char color = (char)this.serialPort.readBytes(1)[0];
        String kleur = "";
        System.out.println(color);
        if(color == 'r') kleur = "Red";
        else if (color == 'g') kleur = "Yellow";
        else if (color == 'b') kleur = "Blue";
        System.out.println(kleur);
            Artikel a = Database.selecteerArtikel(kleur);
            System.out.println(a);
            index = bpp.findBinNum(a);

        if(index == -1) {
            this.serialPort.writeString("0:"); // pusher aan zetten
            this.closePort();
        } else {
            this.closePort();
            inpak.draaiNaarPlatform(index);
            this.serialPort.writeString("1:");
        }
        return index;
    }

    public int getKleur(Bpp bpp) throws SerialPortException, InterruptedException {
        int index = -1;
        String test = "#";

        while(true) {
            if(false) {
                return index;
            }
        }

    }

    public void bandAan() throws SerialPortException {
        try {

            this.serialPort.writeString("0:");}
        catch (SerialPortException e) { }

    }

    public static class SorteerListener implements SerialPortEventListener {
        String buffer = "";

        SerialPort port;

        SorteerScherm sorteerScherm;

        static boolean start = true;

        public SorteerListener(SerialPort port, SorteerScherm sorteerScherm) {
            this.port = port;
            this.sorteerScherm = sorteerScherm;
        }
        private void onMessage() {
            // constructing message
            System.out.println("RECEIVED MESSAGE: " + buffer);
            byte[] bufferBytes = buffer.getBytes();
            for (int i = 0; i < bufferBytes.length; i++) {
                if(bufferBytes[i] == '#') {
                    verstuurData((char) bufferBytes[i+1]);
                    try {
                        this.port.closePort();
                    } catch (SerialPortException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
            buffer = "";
        }

        private void verstuurData(char payload) {
            sorteerScherm.moduleData(payload);
            System.out.println("test");
        }

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0 && port.isOpened()) {
                try {
                    String b = port.readString(event.getEventType());
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
    public class MyPortListener implements SerialPortEventListener {
        String buffer = "";

        private SerialPort port = getSerialPort();

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
